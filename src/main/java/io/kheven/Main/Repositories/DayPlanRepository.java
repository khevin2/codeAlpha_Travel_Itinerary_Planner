package io.kheven.Main.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.kheven.Main.Models.DayPlan;

public interface DayPlanRepository extends MongoRepository<DayPlan, String> {}
