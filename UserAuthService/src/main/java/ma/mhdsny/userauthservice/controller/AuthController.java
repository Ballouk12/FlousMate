package ma.mhdsny.userauthservice.controller;

import ma.mhdsny.userauthservice.entities.User;
import ma.mhdsny.userauthservice.service.AuthService;
import ma.mhdsny.userauthservice.service.UserPublishBridgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RefreshScope
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    UserPublishBridgeService userPublishBridgeService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return authService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User loginRequest) {
        return authService.login(loginRequest);
    }

    @GetMapping("/publish")
    public String publishMessage() {
        userPublishBridgeService.publishUsers();
        return "Message sent to Kafka topic.";
    }
}
