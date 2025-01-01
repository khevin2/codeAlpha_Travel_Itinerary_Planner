package io.kheven.Main.Repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.kheven.Main.Models.Itinerary;

public interface ItineraryRepository extends MongoRepository<Itinerary, String> {
    Optional<Itinerary> findByUserId(String userId);

}
