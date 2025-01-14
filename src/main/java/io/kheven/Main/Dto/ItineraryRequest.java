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
        private String destinations; 
        private String startDate;
        private String endDate; 
        private String preference; 
}
