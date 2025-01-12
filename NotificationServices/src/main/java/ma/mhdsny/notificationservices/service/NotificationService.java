package ma.mhdsny.notificationservices.service;

import ma.mhdsny.notificationservices.entity.Notification;
import java.util.List;

public interface NotificationService {

    Notification createNotification(Notification notification);

    List<Notification> getNotificationsByUserId(Long userId);

    // Additional methods can be added here as needed
}
