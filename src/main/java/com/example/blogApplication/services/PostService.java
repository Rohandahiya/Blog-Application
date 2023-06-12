package com.example.blogApplication.services;

import com.example.blogApplication.dtos.PostDTO;
import com.example.blogApplication.exceptions.RohanException;
import com.example.blogApplication.model.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {


    //create
    PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId) throws RohanException;

    List<PostDTO> getPostByUser(Integer userId) throws RohanException;

    List<PostDTO> getPostByCategory(Integer categoryId) throws RohanException;
    PostDTO updatePost(Integer id,PostDTO postDTO) throws RohanException;

    PostDTO getPostById(Integer Id) throws RohanException;

    List<PostDTO> getAllPost(Integer pageNumber, Integer pageSize);

    void deletePost(Integer id);



}
