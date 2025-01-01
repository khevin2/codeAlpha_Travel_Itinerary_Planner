package io.kheven.Main.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.kheven.Main.Dto.ItineraryRequest;
import io.kheven.Main.Services.AIService;

import java.io.IOException;

@RestController
@RequestMapping("/api/itineraries")
public class ItineraryController {
    @Autowired
    private AIService aiService;

    @PostMapping("/generate")
    public ResponseEntity<?> generateItinerary(@RequestBody ItineraryRequest request) {
        try {
            String itineraryText = aiService.generateItinerary(
                    request.getDestinations(),
                    request.getStartDate(),
                    request.getEndDate(),
                    request.getPreference());

            String parsedItenerary = aiService.parseItinerary(itineraryText);
            return ResponseEntity.status(HttpStatus.OK)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .body("{\"message\":\" Success\",\"itenerary\":\"" + parsedItenerary + "\"}");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error generating itinerary");
        }
    }
}
