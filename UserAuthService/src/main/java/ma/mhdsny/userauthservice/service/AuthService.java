package ma.mhdsny.userauthservice.service;

import ma.mhdsny.userauthservice.entities.User;
import ma.mhdsny.userauthservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public User register(User user) {
        // Ajoutez ici des validations, hashage de mot de passe, vérification de l'unicité, etc.
        return userRepository.save(user);
    }

    public String login(User loginRequest) {
        Optional<User> existing = userRepository.findByEmail(loginRequest.getEmail());
        if (existing.isPresent()) {
            // Comparez le mot de passe hashé et générez un token JWT
            return "JWT-TOKEN-EXAMPLE"; // Placeholder pour un token JWT
        }
        throw new RuntimeException("Invalid credentials");
    }
}
