package ma.mhdsny.categorizationservice.controller;

import ma.mhdsny.categorizationservice.entity.Category;
import ma.mhdsny.categorizationservice.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RefreshScope
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    // Create a new category (optionally specifying a parent)
    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    // Get all categories (for demonstration)
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Get categories by userId
    @GetMapping("/user/{userId}")
    public List<Category> getCategoriesForUser(@PathVariable Long userId) {
        return categoryRepository.findByUserId(userId);
    }

    // Get a single category by ID
    @GetMapping("/{id}")
    public Category getCategory(@PathVariable Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    // Update an existing category
    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category updatedData) {
        return categoryRepository.findById(id).map(existing -> {
            existing.setName(updatedData.getName());
            existing.setUserId(updatedData.getUserId());
            existing.setParentCategory(updatedData.getParentCategory());
            return categoryRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    // Delete a category
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryRepository.deleteById(id);
    }
}
