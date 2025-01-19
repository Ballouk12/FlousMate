package ma.mhdsny.transactionservice.controller;

import ma.mhdsny.transactionservice.entities.Transaction;
import ma.mhdsny.transactionservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@RefreshScope
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.createTransaction(transaction);
    }

    @GetMapping("/user/{userId}")
    public List<Transaction> getUserTransactions(@PathVariable Long userId) {
        return transactionService.getUserTransactions(userId);
    }
}
