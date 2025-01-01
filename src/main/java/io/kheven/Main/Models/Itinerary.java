package io.kheven.Main.Models;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document("itineraries")
public class Itinerary {
    @Id
    private String id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String preference;
    private Double budget;
    private User user;
    private List<Destination> destinations;
}
