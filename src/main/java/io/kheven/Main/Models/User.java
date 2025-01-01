package io.kheven.Main.Models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Document("users")
public class User {
    @Id
    private String id;
    private String username;
    private String email;
    private String password;
    private List<Itinerary> itineraries;
}
