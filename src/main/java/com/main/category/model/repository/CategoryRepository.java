package com.main.category.model.repository;

import com.main.category.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    public Category findByCategoryId(Long categoryId);
    public List<Category> findAll();
}
