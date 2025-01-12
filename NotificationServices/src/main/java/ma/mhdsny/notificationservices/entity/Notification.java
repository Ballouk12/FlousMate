package ma.mhdsny.notificationservices.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String notificationType; // "BUDGET_WARNING", "GOAL_ACHIEVED", etc.
    private String message;
    private boolean isRead;

    private LocalDateTime  timestamp;

    @PrePersist
    void onCreate() {
        this.timestamp = LocalDateTime.now();
    }
}
