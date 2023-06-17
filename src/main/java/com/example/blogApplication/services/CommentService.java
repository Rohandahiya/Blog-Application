package com.example.blogApplication.services;

import com.example.blogApplication.dtos.CommentDTO;
import com.example.blogApplication.dtos.PostDTO;
import com.example.blogApplication.exceptions.RohanException;
import org.yaml.snakeyaml.events.CommentEvent;

import java.util.List;

public interface CommentService {

    CommentDTO addComment(Integer userId,CommentDTO commentDTO,Integer postId) throws RohanException;

    CommentDTO getComment(Integer id) throws RohanException;

    List<CommentDTO> getCommentsByUser(Integer userId) throws RohanException;

    List<CommentDTO> getCommentsOnPost(Integer postId) throws RohanException;

    List<CommentDTO> getAllComments();

    void deleteComment(Integer id);
}
