package com.example.restblog.respository;

import com.example.restblog.data.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Category, String> {
    Category findCategoryByName(String name);
}