package io.kheven.Main.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import io.kheven.Main.Models.Activity;



public interface ActivityRepository extends MongoRepository<Activity, String> {
}
