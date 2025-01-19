package ma.mhdsny.badgetgoalservice.controller;

import ma.mhdsny.badgetgoalservice.entity.Budget;
import ma.mhdsny.badgetgoalservice.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/budgets")
@RefreshScope
public class BudgetController {

    @Autowired
    private BudgetRepository budgetRepository;

    // Create a new budget
    @PostMapping
    public Budget createBudget(@RequestBody Budget budget) {
        return budgetRepository.save(budget);
    }

    // Get all budgets
    @GetMapping
    public List<Budget> getAllBudgets() {
        return budgetRepository.findAll();
    }

    // Get budgets for a specific user
    @GetMapping("/user/{userId}")
    public List<Budget> getBudgetsByUser(@PathVariable Long userId) {
        return budgetRepository.findByUserId(userId);
    }

    // Get a single budget by ID
    @GetMapping("/{id}")
    public Budget getBudgetById(@PathVariable Long id) {
        return budgetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Budget not found with id " + id));
    }

    // Update a budget
    @PutMapping("/{id}")
    public Budget updateBudget(@PathVariable Long id, @RequestBody Budget updatedBudget) {
        return budgetRepository.findById(id).map(budget -> {
            budget.setName(updatedBudget.getName());
            budget.setAmountLimit(updatedBudget.getAmountLimit());
            budget.setStartDate(updatedBudget.getStartDate());
            budget.setEndDate(updatedBudget.getEndDate());
            return budgetRepository.save(budget);
        }).orElseThrow(() -> new RuntimeException("Budget not found with id " + id));
    }

    // Delete a budget
    @DeleteMapping("/{id}")
    public void deleteBudget(@PathVariable Long id) {
        budgetRepository.deleteById(id);
    }
}

