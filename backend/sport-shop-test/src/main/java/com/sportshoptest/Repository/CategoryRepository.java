package com.sportshoptest.Repository;

import com.sportshoptest.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    List<Category> findByCategoryTypeInOrderByCategoryTypeAsc(List<Integer> categoryTypes);
    // All category
    List<Category> findAllByOrderByCategoryType();
    // One category
    Category findByCategoryType(Integer categoryType);
}
