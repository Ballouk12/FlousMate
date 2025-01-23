package ma.mhdsny.challengesservice.service;

import dto.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Component
public class UsersConsumer {
    private Map<Long, User> cashUsers = new HashMap<>();
    private List<User> cashUsersList = new ArrayList<>();

    @Bean
    public Consumer<List<User>> usersTopicConsumer() {
        return users -> {
            cashUsersList.addAll(users);
            for(User user : users) {
                cashUsers.put(user.getId(), user);
                System.out.println("Un autre user consumer de puis le topic : ======> " + user);
            }
            System.out.println("Le message consumer de puis le topic : ======> " + users);
        };
    }

    public User getUserById(long id) {
        try {
            return cashUsers.get(id);
        } catch (NullPointerException e) {
            return null;
        }
    }

    public List<User> getAllUsers() {
        return cashUsersList;
    }
}