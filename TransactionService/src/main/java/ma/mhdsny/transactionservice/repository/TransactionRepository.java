package ma.mhdsny.transactionservice.repository;


import ma.mhdsny.transactionservice.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUserId(Long userId);
    List<Transaction> findByAccountId(Long accountId);
}
