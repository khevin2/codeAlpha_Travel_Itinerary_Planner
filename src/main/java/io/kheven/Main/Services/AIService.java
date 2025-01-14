package io.kheven.Main.Services;

import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.List;
import java.util.ArrayList;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AIService {
    private static final String OPENAI_API_KEY = Dotenv.load().get("OPENAI_API_KEY");
    private static final String OPENAI_API_URL = Dotenv.load().get("OPENAI_API_URL");
    private static final String OPENAI_API_MODEL = Dotenv.load().get("OPENAI_API_MODEL");
    private static final String OPENAI_API_TEMPERATURE = Dotenv.load().get("OPENAI_API_TEMPERATURE");

    private final OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(45, TimeUnit.SECONDS)
    .readTimeout(45, TimeUnit.SECONDS).writeTimeout(45, TimeUnit.SECONDS).build();
    private static ObjectMapper objectMapper = new ObjectMapper();

    public String generateItinerary(String destinations, String startDate, String endDate, String preference)
            throws IOException {
        String prompt = String.format(
                "Create a detailed travel itinerary from %s to %s.\n"
                        + "Destinations: %s.\n"
                        + "Preference: %s.\n"
                        + "The response should be in JSON format with the following structure:\n"
                        + "{\n"
                        + "  \"itinerary\": [\n"
                        + "    {\n"
                        + "      \"day\": \"Day X\",\n"
                        + "      \"date\": \"Month Day\",\n"
                        + "      \"activities\": [\n"
                        + "        {\n"
                        + "          \"time\": \"Morning/Afternoon/Evening\",\n"
                        + "          \"description\": \"Activity description.\"\n"
                        + "        }\n"
                        + "      ],\n"
                        + "      \"locations\": [\"Location1\", \"Location2\"]\n"
                        + "    }\n"
                        + "  ]\n"
                        + "}\n"
                        + "Make sure the response strictly follows this format.",
                startDate, endDate, destinations, preference);

        System.out.println("Prompt: " + prompt + "\n");
                System.out.println("Model: " + OPENAI_API_MODEL + "\n" + "Temperature: " + OPENAI_API_TEMPERATURE + "\n" + "API URL: " + OPENAI_API_URL + "\n");

        MediaType mediaType = MediaType.parse("application/json");
      

        Map<String, String> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", prompt);

        List<Map<String, String>> messages = new ArrayList<>();
        messages.add( message);

        Map<String, Object> req = new HashMap<>();
        req.put("messages", messages);
        req.put("model", OPENAI_API_MODEL);
        req.put("temperature", Double.parseDouble(OPENAI_API_TEMPERATURE));


        String json = objectMapper.writeValueAsString(req);

        System.out.println("JSON: " + json + "\n");
                
        RequestBody body = RequestBody.create(json, mediaType);
        Request request = new Request.Builder()
                .url(OPENAI_API_URL)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + OPENAI_API_KEY)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        }
    }

    public String parseItinerary(String itineraryText) throws IOException {
        String prompt = "Convert the following itinerary text into a structured JSON format with fields for day, activities, and locations:\n\n"
                + itineraryText;

        MediaType mediaType = MediaType.parse("application/json");
                Map<String, String> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", prompt);

        List<Map<String, String>> messages = new ArrayList<>();
        messages.add( message);

        Map<String, Object> req = new HashMap<>();
        req.put("messages", messages);
        req.put("model", OPENAI_API_MODEL);


        String json = objectMapper.writeValueAsString(req);

        System.out.println("json2: " + json + "\n");

        RequestBody body = RequestBody.create(json, mediaType);
        Request request = new Request.Builder()
                .url(OPENAI_API_URL)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + OPENAI_API_KEY)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code:: JSON :: " + response);
            }
            return response.body().string();
        }

    }

}
