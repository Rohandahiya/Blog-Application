package com.example.blogApplication.controllers;

import com.example.blogApplication.dtos.PostDTO;
import com.example.blogApplication.exceptions.RohanException;
import com.example.blogApplication.model.Post;
import com.example.blogApplication.services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apis/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/add")
    public ResponseEntity<?> postUser(@RequestBody PostDTO postDTO, @RequestParam(value = "userId",required = true) Integer userId, @RequestParam(value="categoryId",required = true) Integer categoryId){

        try {
            PostDTO response = postService.createPost(postDTO,userId,categoryId);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (RohanException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/userId/{user_id}")
    public ResponseEntity<?> getPostsByUser(@PathVariable("user_id") Integer userId){

        try {
            List<PostDTO> response = postService.getPostByUser(userId);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (RohanException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @GetMapping("/category/{category_id}")
    public ResponseEntity<?> getPostsByCategory(@PathVariable("category_id") Integer categoryId){

        try {
            List<PostDTO> response = postService.getPostByCategory(categoryId);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (RohanException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }
    @GetMapping("/{post_id}")
    public ResponseEntity<?> getPostsById(@PathVariable("post_id") Integer postId){

        try {
            PostDTO response = postService.getPostById(postId);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (RohanException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @GetMapping("/all")
    public ResponseEntity<?> getPostsById(@RequestParam(defaultValue = "10",required = false) Integer pageNumber,@RequestParam(defaultValue = "1",required = false) Integer pageSize){

            List<PostDTO> response = postService.getAllPost(pageNumber,pageSize);
            return ResponseEntity.status(HttpStatus.OK).body(response);


    }

    @PutMapping("/update/{post_id}")
    public ResponseEntity<?> updatePost(@RequestBody PostDTO postDTO,@PathVariable("post_id") Integer postId){

        try {
            PostDTO response = postService.updatePost(postId,postDTO);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (RohanException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @DeleteMapping("/delete/{post_id}")
    public  ResponseEntity<?> deletePost(@PathVariable("post_id") Integer id){

        postService.deletePost(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted");
    }


}
