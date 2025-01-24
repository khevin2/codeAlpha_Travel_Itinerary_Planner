package io.kheven.Main.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.kheven.Main.Dto.SaveItineraryRequest;
import io.kheven.Main.Models.Activity;
import io.kheven.Main.Models.DayPlan;
import io.kheven.Main.Repositories.ActivityRepository;
import io.kheven.Main.Repositories.DayPlanRepository;

@Service
public class IteneraryService {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private DayPlanRepository dayPlanRepository;
    
    @Transactional
    public void commitItenaries(SaveItineraryRequest request) {
        for (DayPlan dayPlan : request.getItinerary()) {
            for (Activity activity : dayPlan.getActivities()) {
                activityRepository.save(activity);
            }
            dayPlanRepository.save(dayPlan);
        }
    }
}
