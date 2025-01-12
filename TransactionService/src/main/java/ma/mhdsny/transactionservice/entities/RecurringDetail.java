package ma.mhdsny.transactionservice.entities;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "recurring_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecurringDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    private String frequency;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer limitOccurrences;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
