package com.main.playlist.model.respository;

import com.main.playlist.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    public Category findByCategoryId(Long categoryId);
}
