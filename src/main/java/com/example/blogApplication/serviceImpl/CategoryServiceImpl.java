package com.example.blogApplication.serviceImpl;

import com.example.blogApplication.dtos.CategoryDTO;
import com.example.blogApplication.exceptions.RohanException;
import com.example.blogApplication.model.Category;
import com.example.blogApplication.repositories.CategoryRepository;
import com.example.blogApplication.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public CategoryDTO getCategoryById(Integer id) throws RohanException {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RohanException("Category","Id",id));
        CategoryDTO categoryDTO = modelMapper.map(category,CategoryDTO.class);

        return categoryDTO;
    }

    @Override
    public Category addCategory(CategoryDTO categoryDTO) {

        Category category = modelMapper.map(categoryDTO,Category.class);

        category = categoryRepository.save(category);

        return category;

    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId) throws RohanException {

       Category category = categoryRepository.findById(categoryId).orElseThrow(()->new RohanException("Category","Id",categoryId));

       category.setCategoryDescription(categoryDTO.getCategoryDescription());
       category.setCategoryTitle(categoryDTO.getCategoryTitle());
       categoryRepository.save(category);
       return modelMapper.map(category,CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getAllUsers() {
        return categoryRepository.findAll().stream().map(cat -> modelMapper.map(cat,CategoryDTO.class)).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer categoryId) throws RohanException {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()->new RohanException("Category","Id",categoryId));

        categoryRepository.delete(category);

        return;
    }


}
