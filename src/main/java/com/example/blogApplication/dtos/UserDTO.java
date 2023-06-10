package com.example.blogApplication.dtos;

import com.example.blogApplication.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserDTO {
    private int Id;

    @NotNull
    private String name;

    @NotNull
    @Size(min = 4,message = "Should be at least 4 characters")
    @Pattern(regexp = "^(?=.*[0-9]).+$")
    private String password;

    @Email
    private String email;

    private String about;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public User toPO(){
        User user = new User();
        user.setId(this.Id);
        user.setAbout(this.about);
        user.setPassword(this.password);
        user.setName(this.name);
        user.setEmail(this.email);

        return user;
    }
}
