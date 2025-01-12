package ma.mhdsny.notificationservices.repository;

import ma.mhdsny.notificationservices.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    // Custom query method to find notifications by userId
    List<Notification> findByUserId(Long userId);

    // Additional query methods can be added here as needed
}
