package com.example.blogApplication.controllers;

import com.example.blogApplication.AppConstants;
import com.example.blogApplication.dtos.PostDTO;
import com.example.blogApplication.exceptions.RohanException;
import com.example.blogApplication.model.Post;
import com.example.blogApplication.responses.PostPageResponse;
import com.example.blogApplication.services.FileService;
import com.example.blogApplication.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/apis/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.path}")
    private String filePath;

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
    public ResponseEntity<?> getPostsById(@RequestParam(defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber, @RequestParam(defaultValue = AppConstants.PAGE_SIzE,required = false) Integer pageSize, @RequestParam(defaultValue = AppConstants.SORT_VALUE,required = false) String sortBy){

            PostPageResponse response = postService.getAllPost(pageNumber,pageSize,sortBy);
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

    @GetMapping("/get/keyword/{searchString}")
    public ResponseEntity<?> getPostsOnSearchString(@PathVariable("searchString") String keyword){

        List<PostDTO> response = postService.searchPost(keyword);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @DeleteMapping("/delete/{post_id}")
    public  ResponseEntity<?> deletePost(@PathVariable("post_id") Integer id){

        postService.deletePost(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted");
    }

    @PostMapping("/upload/image")
    public ResponseEntity<?> uploadImage(@RequestParam("file")MultipartFile file){

        if(file.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No file in request");
        }

        if(!(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")).equalsIgnoreCase(".jpg"))){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only jpg images allowed");
        }

        try {
            fileService.uploadImage(filePath,file);
            return ResponseEntity.status(HttpStatus.OK).body("File successfully uploaded");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping(value = "/get/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<?> getImage(@PathVariable("imageName") String imagename, HttpServletResponse httpServletResponse){

        try {
            InputStream inputStream = fileService.getImage(filePath,imagename);
            httpServletResponse.setContentType(MediaType.IMAGE_JPEG_VALUE);
            StreamUtils.copy(inputStream,httpServletResponse.getOutputStream());
            return ResponseEntity.status(HttpStatus.OK).body("downloded");
        } catch (FileNotFoundException e) {
            return ResponseEntity.status(HttpStatus.OK).body("error occured");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
