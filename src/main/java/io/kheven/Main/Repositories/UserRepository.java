package io.kheven.Main.Repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.kheven.Main.Models.User;

public interface UserRepository  extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
}
