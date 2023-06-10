package com.example.blogApplication.services;

import com.example.blogApplication.dtos.CategoryDTO;
import com.example.blogApplication.exceptions.RohanException;
import com.example.blogApplication.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    public CategoryDTO getCategoryById(Integer id) throws RohanException;

    public Category addCategory(CategoryDTO categoryDTO);

    CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId) throws RohanException;

    List<CategoryDTO> getAllUsers();

    void delete(Integer categoryId) throws RohanException;
}
