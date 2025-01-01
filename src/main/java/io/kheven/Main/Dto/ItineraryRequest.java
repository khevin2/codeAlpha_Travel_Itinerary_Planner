package io.kheven.Main.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ItineraryRequest {    
        private String destinations; // e.g., "Paris, Rome, Berlin"
        private String startDate; // e.g., "2024-06-01"
        private String endDate; // e.g., "2024-06-15"
        private String preference; // e.g., "Adventure"
}
