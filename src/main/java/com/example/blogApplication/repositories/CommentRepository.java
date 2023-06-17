package com.example.blogApplication.repositories;

import com.example.blogApplication.model.Comment;
import com.example.blogApplication.model.Post;
import com.example.blogApplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {


    List<Comment> findByUser(User user);

    List<Comment> findByPost(Post post);
}
