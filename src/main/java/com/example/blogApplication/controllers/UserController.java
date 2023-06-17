package com.example.blogApplication.controllers;

import com.example.blogApplication.dtos.UserDTO;
import com.example.blogApplication.exceptions.RohanException;
import com.example.blogApplication.services.UserService;
import javax.persistence.*;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/apis/users")
public class UserController {

    @Autowired
    @Qualifier("mainServiceImpl")
    private UserService userService;

    @PostMapping("/post")
    public ResponseEntity<UserDTO> postUser(@Valid @RequestBody(required = true) UserDTO userDTO){

        UserDTO user = userService.createUser(userDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);

    }

    @PutMapping("/update/{user_id}")
    public ResponseEntity<?> updatedUser(@RequestBody UserDTO userDTO,@PathVariable("user_id") Integer userId){

        try{
            UserDTO responseUserDTO = userService.updateUser(userDTO,userId);
            return ResponseEntity.status(HttpStatus.OK).body(responseUserDTO);
        }catch (RohanException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @GetMapping("/{user_id}")
    public ResponseEntity<?> updatedUser(@PathVariable("user_id") Integer userId){

        try{
            UserDTO responseUserDTO = userService.getUserById(userId);
            return ResponseEntity.status(HttpStatus.OK).body(responseUserDTO);
        }catch (RohanException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO){

            UserDTO responseUserDTO = userService.registerNewUser(userDTO);
            return ResponseEntity.status(HttpStatus.OK).body(responseUserDTO);

    }

    @GetMapping("/all")
    public ResponseEntity<?> getUsers(){

            List<UserDTO> userDTOs =  userService.getAllUsers();
            return ResponseEntity.status(HttpStatus.OK).body(userDTOs);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer id){

        try {
            userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted");
        } catch (RohanException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }
}
