package io.kheven.Main.Services;

import java.util.Optional;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.kheven.Main.Models.User;
import io.kheven.Main.Repositories.UserRepository;

@Service
public class UserService {
     @Autowired
     private UserRepository userRepository;
    
     @Autowired
     JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Map<String, Object> login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            if (passwordEncoder.matches(password, user.get().getPassword())) {
                Map<String, Object> response = new HashMap<>();
                response.put("username", user.get().getUsername());
                response.put("email", user.get().getEmail());
                response.put("token", jwtUtil.generateToken(user.get().getEmail()));
                return response;
            }
        }
        return null;
    }
}
