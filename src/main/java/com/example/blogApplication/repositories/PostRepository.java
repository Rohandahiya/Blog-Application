package com.example.blogApplication.repositories;

import com.example.blogApplication.model.Category;
import com.example.blogApplication.model.Post;
import com.example.blogApplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {


    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);
}
