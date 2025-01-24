package io.kheven.Main.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document("day_plan")
public class DayPlan {
     @Id
    private String id;
    private String day;
    private String date;
    private List<Activity> activities;
    private List<String> locations;
}