package ma.mhdsny.challengesservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "challenge_participation")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChallengeParticipation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Which user is participating (if you have a separate User service, store userId)
    private Long userId;

    // The specific challenge the user is trying
    private Long challengeId;

    // Current progress toward the goalValue
    private double currentProgress;
    private boolean completed;

    private LocalDateTime completedAt;
    private LocalDateTime updatedAt;

    @PreUpdate
    void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
