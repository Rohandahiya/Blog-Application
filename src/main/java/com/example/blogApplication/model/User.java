package com.example.blogApplication.model;

import com.example.blogApplication.dtos.UserDTO;
import jakarta.persistence.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Password")
    private String password;

    @Column(name = "Email")
    private String email;

    @Column(name = "About")
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
    
    public UserDTO toDTO(){
        UserDTO userDTO = new UserDTO();

        userDTO.setId(this.Id);
        userDTO.setAbout(this.about);
        userDTO.setPassword(this.password);
        userDTO.setName(this.name);
        userDTO.setEmail(this.email);

        return userDTO;
    }
}
