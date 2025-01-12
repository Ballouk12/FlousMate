package ma.mhdsny.transactionservice.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "split_lines")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SplitLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    private Long categoryId;

    private double amount;
}
