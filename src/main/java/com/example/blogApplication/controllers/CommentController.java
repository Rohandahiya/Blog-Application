package com.example.blogApplication.controllers;

import com.example.blogApplication.dtos.CommentDTO;
import com.example.blogApplication.dtos.PostDTO;
import com.example.blogApplication.exceptions.RohanException;
import com.example.blogApplication.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apis/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/add")
    public ResponseEntity<?> postComment(@RequestBody CommentDTO commentDTO, @RequestParam(value = "userId",required = true) Integer userId, @RequestParam(value="postId",required = true) Integer postId){

        try {
            CommentDTO response = commentService.addComment(userId,commentDTO,postId);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (RohanException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/commentId/{comment_id}")
    public ResponseEntity<?> getCommentById(@PathVariable("comment_id") Integer commentId){

        try {
            CommentDTO response = commentService.getComment(commentId);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (RohanException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @GetMapping("/user/{user_id}")
    public ResponseEntity<?> getCommentByUser(@PathVariable("user_id") Integer userId){

        try {
            List<CommentDTO> response = commentService.getCommentsByUser(userId);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (RohanException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @GetMapping("/post/{post_id}")
    public ResponseEntity<?> getCommentOnPost(@PathVariable("post_id") Integer postId){

        try {
            List<CommentDTO> response = commentService.getCommentsOnPost(postId);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (RohanException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @GetMapping("/all")
    public ResponseEntity<?> getCommentOnPost(){

            List<CommentDTO> response = commentService.getAllComments();
            return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable("id") Integer id){

        commentService.deleteComment(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted");

    }

}
