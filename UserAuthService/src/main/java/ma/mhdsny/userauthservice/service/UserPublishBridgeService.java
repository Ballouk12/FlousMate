package ma.mhdsny.userauthservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ma.mhdsny.userauthservice.entities.User;
import ma.mhdsny.userauthservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPublishBridgeService {
    @Autowired
    private StreamBridge streamBridge;
    @Autowired
    private UserRepository userRepository;
    public void publishUsers() {
            publish(userRepository.findAll());

    }

    private void publish(List<User> users) {
        boolean sent = streamBridge.send("usersTopic-out-0", users);  // Changé de output-out-0 à usersTopic-out-0

        if (sent) {
            System.out.println("Message successfully sent to Kafka topic: =====> " + users);
        } else {
            System.err.println("Failed to send message to Kafka topic.");
        }
    }


}
