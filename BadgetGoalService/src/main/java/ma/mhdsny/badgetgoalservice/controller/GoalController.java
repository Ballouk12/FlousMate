package ma.mhdsny.badgetgoalservice.controller;

import ma.mhdsny.badgetgoalservice.entity.Goal;
import ma.mhdsny.badgetgoalservice.repository.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goals")
public class GoalController {

    @Autowired
    private GoalRepository goalRepository;

    // Create a new goal
    @PostMapping
    public Goal createGoal(@RequestBody Goal goal) {
        return goalRepository.save(goal);
    }

    // Get all goals
    @GetMapping
    public List<Goal> getAllGoals() {
        return goalRepository.findAll();
    }

    // Get goals for a specific user
    @GetMapping("/user/{userId}")
    public List<Goal> getGoalsByUser(@PathVariable Long userId) {
        return goalRepository.findByUserId(userId);
    }

    // Get a single goal by ID
    @GetMapping("/{id}")
    public Goal getGoalById(@PathVariable Long id) {
        return goalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Goal not found with id " + id));
    }

    // Update a goal
    @PutMapping("/{id}")
    public Goal updateGoal(@PathVariable Long id, @RequestBody Goal updatedGoal) {
        return goalRepository.findById(id).map(goal -> {
            goal.setName(updatedGoal.getName());
            goal.setTargetAmount(updatedGoal.getTargetAmount());
            goal.setCurrentProgress(updatedGoal.getCurrentProgress());
            goal.setDueDate(updatedGoal.getDueDate());
            return goalRepository.save(goal);
        }).orElseThrow(() -> new RuntimeException("Goal not found with id " + id));
    }

    // Delete a goal
    @DeleteMapping("/{id}")
    public void deleteGoal(@PathVariable Long id) {
        goalRepository.deleteById(id);
    }
}
