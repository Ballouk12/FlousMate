package ma.mhdsny.userauthservice.controller;

import ma.mhdsny.userauthservice.entities.User;
import ma.mhdsny.userauthservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return authService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User loginRequest) {
        return authService.login(loginRequest);
    }
}
