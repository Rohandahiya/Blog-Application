package com.example.blogApplication.dtos;

import com.example.blogApplication.model.Post;
import com.example.blogApplication.model.User;
import javax.persistence.*;

import java.util.Date;

public class CommentDTO {

    private int id;

    private String content;

    private Date madeOn;

    private UserDTO user;

    private PostDTO post;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getMadeOn() {
        return madeOn;
    }

    public void setMadeOn(Date madeOn) {
        this.madeOn = madeOn;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public PostDTO getPost() {
        return post;
    }

    public void setPost(PostDTO post) {
        this.post = post;
    }
}
