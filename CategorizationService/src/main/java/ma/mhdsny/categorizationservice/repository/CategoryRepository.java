package ma.mhdsny.categorizationservice.repository;

import ma.mhdsny.categorizationservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // If you want to find categories by userId
    List<Category> findByUserId(Long userId);

    // Optionally, find top-level categories (where parentCategory is null)
    List<Category> findByParentCategoryIsNull();
}
