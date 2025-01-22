package ma.mhdsny.notificationservices.service;


import dto.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Service
public class UserCosumerService {
    private Map<Long, User> usersCache = new HashMap<>();
    private List<User> usersList  = new ArrayList<>();

    @Bean
    Consumer<List<User>> usersTopicConsumer() {
        return users -> {
            usersList.clear();
            usersList.addAll(users);
            for(User user : users) {
                usersCache.put(user.getId(),user);
            }
            System.out.println("Le message consumer de puis le topic usersTopic : ======> " + users);
        };
    }

    public List<User> getallUsers() {
        return usersList;
    }
    public User getUserById(long id) {
        try {
            return usersCache.get(id);
        } catch(NullPointerException e) {
            return null;
        }
    }

}
