package com.example.blogApplication.dtos;

import com.example.blogApplication.model.Category;
import com.example.blogApplication.model.User;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;

import java.awt.*;
import java.util.Date;

public class PostDTO {


    private Integer id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    private String imgUrl = "https://picsum.photos/200/300/?blur";

    private UserDTO user;

    private CategoryDTO category;

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
