package com.example.blogApplication.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import javax.validation.constraints.*;

public class CategoryDTO {

    @JsonProperty(value = "id")
    private Integer categoryId;

    @NotNull
    @JsonProperty(value = "title")
    @Size(min = 4)
    private String categoryTitle;

    @JsonProperty(value = "description")
    @Size(min =10)
    private String categoryDescription;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }
}
