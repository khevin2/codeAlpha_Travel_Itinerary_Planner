package io.kheven.Main.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.kheven.Main.Dto.LoginRequest;
import io.kheven.Main.Models.User;
import io.kheven.Main.Services.UserService;

import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Validated @RequestBody User user) {
      
        User savedUser = userService.registerUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Validated @RequestBody LoginRequest req) {
        Map<String, Object> res = userService.login(req.email(), req.password());
        if (res == null) {
            return ResponseEntity.badRequest().body("{\"error\": true,\"message\":\"Invalid credentials\"}");
        }

        Map<String, Object> successResponse = new HashMap<>();
        successResponse.put("message", "Success");
        successResponse.put("data", res);
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(successResponse);
    }
}
