package com.example.blogApplication.controllers;

import com.example.blogApplication.dtos.CategoryDTO;
import com.example.blogApplication.exceptions.RohanException;
import com.example.blogApplication.model.Category;
import com.example.blogApplication.payload.ResponseDTO;
import com.example.blogApplication.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/apis/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/get/{category_id}")
    public ResponseEntity<?> getCategory(@PathVariable("category_id") Integer id){

        ResponseDTO responseDTO = new ResponseDTO();

        try {
            CategoryDTO categoryDTO = categoryService.getCategoryById(id);
            return ResponseEntity.status(HttpStatus.OK).body(categoryDTO);
        } catch (RohanException e) {
            responseDTO.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setData(null);
            return ResponseEntity.status(responseDTO.getHttpStatus()).body(responseDTO);
        }

    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAllCategories(){

       List<CategoryDTO> categories = categoryService.getAllUsers();

       return ResponseEntity.status(HttpStatus.OK).body(categories);

    }

    @PostMapping("/add")
    public ResponseEntity<?> postCategory(@Valid @RequestBody(required = true) CategoryDTO categoryDTO) {

        ResponseDTO responseDTO = new ResponseDTO();

        Category category = categoryService.addCategory(categoryDTO);
        return ResponseEntity.status(HttpStatus.OK).body(category);

    }

    @PutMapping("/update/{category_id}")
    public ResponseEntity<?> updateCategory(@RequestBody CategoryDTO categoryDTO,@PathVariable("category_id") Integer categoryId){

         ResponseDTO responseDTO = new ResponseDTO();

         try {
             CategoryDTO updatedCategory = categoryService.updateCategory(categoryDTO,categoryId);
             return ResponseEntity.status(HttpStatus.OK).body(updatedCategory);
         } catch (RohanException e) {
             responseDTO.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
             responseDTO.setMessage(e.getMessage());
             responseDTO.setData(null);
             return ResponseEntity.status(responseDTO.getHttpStatus()).body(responseDTO);
         }

     }

    @DeleteMapping("/delete/{category_id}")
    public ResponseEntity<?> updateCategory(@PathVariable("category_id") Integer categoryId){

        ResponseDTO responseDTO = new ResponseDTO();

        try {
            categoryService.delete(categoryId);
            return ResponseEntity.status(HttpStatus.OK).body("deleted category");
        } catch (RohanException e) {
            responseDTO.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setData(null);
            return ResponseEntity.status(responseDTO.getHttpStatus()).body(responseDTO);
        }

    }



}
