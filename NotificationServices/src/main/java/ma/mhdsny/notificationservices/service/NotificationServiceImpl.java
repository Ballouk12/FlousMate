package ma.mhdsny.notificationservices.service;


import ma.mhdsny.notificationservices.entity.Notification;
import ma.mhdsny.notificationservices.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    @Autowired
    private StreamBridge streamBridge;

    @Override
    public void publishNotifications() {
        List<Notification> notifications = notificationRepository.findAll();
        boolean sent = streamBridge.send("notificationsTopic-out-0",notifications);
        if(sent) {
            System.out.println("Notifications sent successfully to kafka topic");
        } else {
            System.err.println("Notifications failed to send to kafka topic");
        }
    }

    // Constructor Injection is preferred over field injection
    @Autowired
    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Notification createNotification(Notification notification) {
        // Additional business logic can be added here
        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getNotificationsByUserId(Long userId) {
        // Additional business logic can be added here
        return notificationRepository.findByUserId(userId);
    }

}

