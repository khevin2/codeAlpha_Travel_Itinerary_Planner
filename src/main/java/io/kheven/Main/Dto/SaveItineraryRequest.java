package io.kheven.Main.Dto;

import java.util.List;

import io.kheven.Main.Models.DayPlan;
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
public class SaveItineraryRequest {
    private List<DayPlan> itinerary;

}




