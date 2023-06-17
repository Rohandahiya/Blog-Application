package com.example.blogApplication.serviceImpl;

import com.example.blogApplication.AppConstants;
import com.example.blogApplication.dtos.UserDTO;
import com.example.blogApplication.exceptions.RohanException;
import com.example.blogApplication.model.Role;
import com.example.blogApplication.model.User;
import com.example.blogApplication.repositories.RoleRepository;
import com.example.blogApplication.repositories.UserRepository;
import com.example.blogApplication.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = userDTO.toPO();
        User saveUser = userRepository.save(user);
        return saveUser.toDTO();
    }

    @Override
    public UserDTO registerNewUser(UserDTO userDTO) {

        User user = modelMapper.map(userDTO,User.class);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role role = roleRepository.findById(AppConstants.RoleIdNormal).get();

        user.setRoles(Arrays.asList(role));

        User getFinalUser = userRepository.save(user);

        return modelMapper.map(getFinalUser,UserDTO.class);
    }

    @Override
    public UserDTO getUserById(Integer id) throws RohanException {
         User user = userRepository.findById(id).orElseThrow(() -> new RohanException("User","Id",id));
         return user.toDTO();
    }

    @Override
    public List<UserDTO> getAllUsers() {

       return userRepository.findAll().stream().map(user -> user.toDTO()).collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO,Integer id) throws RohanException {
        User user = userRepository.findById(id).orElseThrow(()->new RohanException("User","Id",Integer.valueOf(id)));

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setAbout(userDTO.getAbout());
        user.setPassword(userDTO.getPassword());
        userDTO.setId(user.getId());
        userRepository.save(user);
        return userDTO;
    }

    @Override
    public void deleteUser(Integer userId) throws RohanException {
        User user = userRepository.findById(userId).orElseThrow(() -> new RohanException("User","Id",userId));

        userRepository.delete(user);

    }
}
