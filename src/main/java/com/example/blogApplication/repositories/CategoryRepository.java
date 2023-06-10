package com.example.blogApplication.repositories;

import com.example.blogApplication.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {


}
