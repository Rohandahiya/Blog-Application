package com.example.blogApplication.services;

import com.example.blogApplication.dtos.UserDTO;
import com.example.blogApplication.exceptions.RohanException;
import com.example.blogApplication.model.User;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserDTO createUser(UserDTO userDTO);

    UserDTO registerNewUser(UserDTO userDTO);
    UserDTO getUserById(Integer id) throws RohanException;

    List<UserDTO> getAllUsers();

    UserDTO updateUser(UserDTO userDTO,Integer id) throws RohanException;

    void deleteUser(Integer userId) throws RohanException;

}
