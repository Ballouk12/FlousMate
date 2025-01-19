package ma.mhdsny.userauthservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ma.mhdsny.userauthservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class UserPublishBridgeService {
    @Autowired
    private StreamBridge streamBridge;
    @Autowired
    private UserRepository userRepository;
    private ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    public void publishUsers() {
        try {
            String usersJson = objectMapper.writeValueAsString(userRepository.findAll());
            publish(usersJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void publish(String message) {
        boolean sent = streamBridge.send("usersTopic-out-0", message);  // Changé de output-out-0 à usersTopic-out-0

        if (sent) {
            System.out.println("Message successfully sent to Kafka topic: =====> " + message);
        } else {
            System.err.println("Failed to send message to Kafka topic.");
        }
    }


}
