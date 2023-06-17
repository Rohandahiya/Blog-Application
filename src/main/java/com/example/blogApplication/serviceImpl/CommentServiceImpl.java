package com.example.blogApplication.serviceImpl;

import com.example.blogApplication.dtos.CommentDTO;
import com.example.blogApplication.exceptions.RohanException;
import com.example.blogApplication.model.Comment;
import com.example.blogApplication.model.Post;
import com.example.blogApplication.model.User;
import com.example.blogApplication.repositories.CommentRepository;
import com.example.blogApplication.repositories.PostRepository;
import com.example.blogApplication.repositories.UserRepository;
import com.example.blogApplication.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CommentDTO addComment(Integer userId, CommentDTO commentDTO, Integer postId) throws RohanException {

        User user = userRepository.findById(userId).orElseThrow(()->new RohanException("User","Id",userId));

        Post post = postRepository.findById(postId).orElseThrow(()->new RohanException("Post","Id",postId));

        Comment comment = modelMapper.map(commentDTO, Comment.class);
        comment.setUser(user);
        comment.setPost(post);
        comment.setMadeOn(new Date());

        commentRepository.save(comment);

        return modelMapper.map(comment,CommentDTO.class);
    }

    @Override
    public CommentDTO getComment(Integer id) throws RohanException {

        Comment comment = commentRepository.findById(id).orElseThrow(() -> new RohanException("Comment","Id",id));

        return modelMapper.map(comment,CommentDTO.class);

    }

    @Override
    public List<CommentDTO> getCommentsByUser(Integer userId) throws RohanException {

        User user = userRepository.findById(userId).orElseThrow(()->new RohanException("User","Id",userId));

        List<Comment>comments = commentRepository.findByUser(user);

        return comments.stream().map(comment -> modelMapper.map(comment,CommentDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<CommentDTO> getCommentsOnPost(Integer postId) throws RohanException {
        Post post = postRepository.findById(postId).orElseThrow(()->new RohanException("Post","Id",postId));

        List<Comment> comments = commentRepository.findByPost(post);

        return comments.stream().map(comment -> modelMapper.map(comment,CommentDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<CommentDTO> getAllComments() {
        List<Comment> comments = commentRepository.findAll();

        return comments.stream().map(comment -> modelMapper.map(comment,CommentDTO.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteComment(Integer id) {
        commentRepository.deleteById(id);
    }
}
