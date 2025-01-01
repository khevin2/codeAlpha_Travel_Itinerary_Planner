package io.kheven.Main.Services;

import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

import org.springframework.stereotype.Service;

@Service
public class AIService {
    private static final String OPENAI_API_KEY = Dotenv.load().get("OPENAI_API_KEY");
    private static final String OPENAI_API_URL = Dotenv.load().get("OPENAI_API_URL");
    private static final String OPENAI_API_MODEL = Dotenv.load().get("OPENAI_API_MODEL");

    private final OkHttpClient client = new OkHttpClient();

    public String generateItinerary(String destinations, String startDate, String endDate, String preference)
            throws IOException {
        String prompt = String.format(
                "Create a detailed travel itinerary from %s to %s.\nDestinations: %s.\nPreference: %s.\nInclude daily activities, estimated time, and places to visit.",
                startDate, endDate, destinations, preference);

        MediaType mediaType = MediaType.parse("application/json");
        String json = "{\n" +
                "  \"model\": \"" + OPENAI_API_MODEL + "\",\n" +
                "  \"prompt\": \"" + prompt + "\",\n" +
                "  \"max_tokens\": 1000,\n" +
                "  \"temperature\": 0.7\n" +
                "}";
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
            // Parse the response JSON to extract the itinerary text
            // For simplicity, returning the raw response body as a string
            return response.body().string();
        }
    }

    public String parseItinerary(String itineraryText) throws IOException {
        String prompt = "Convert the following itinerary text into a structured JSON format with fields for day, activities, and locations:\n\n"
                + itineraryText;

        MediaType mediaType = MediaType.parse("application/json");
        String json = "{\n" +
                "  \"model\": \"" + OPENAI_API_MODEL + "\",\n" +
                "  \"prompt\": \"" + prompt + "\",\n" +
                "  \"max_tokens\": 1000,\n" +
                "  \"temperature\": 0.7\n" +
                "}";

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
            // Parse the response JSON to extract the itinerary text
            // For simplicity, returning the raw response body as a string
            return response.body().string();
        }

    }

}
