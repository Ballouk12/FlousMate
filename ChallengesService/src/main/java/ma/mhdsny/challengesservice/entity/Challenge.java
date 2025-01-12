package ma.mhdsny.challengesservice.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "challenges")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;            // e.g., "No Dining Out for 7 Days"
    private String description;     // Explanation of what to do
    private double goalValue;       // e.g., 7 days, or "spend < 2000"

    @Enumerated(EnumType.STRING)
    private ChallengeStatus status; // ACTIVE, INACTIVE, etc.

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
