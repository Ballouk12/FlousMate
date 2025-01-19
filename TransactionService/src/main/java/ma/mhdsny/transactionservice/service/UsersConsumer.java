package ma.mhdsny.transactionservice.service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import java.util.function.Consumer;

@Component
public class UsersConsumer {

    @Bean
    public Consumer<String> usersTopicConsumer() {  // Changé de Consumer<Message<String>> à Consumer<String>
        return message -> {
            System.out.println("Le message consumer de puis le topic : ======> " + message);
        };
    }
}