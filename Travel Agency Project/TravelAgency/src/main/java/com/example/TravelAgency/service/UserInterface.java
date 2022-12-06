package com.example.TravelAgency.service;

import com.example.TravelAgency.dtos.UserDTO;
import com.example.TravelAgency.model.User;

import java.util.List;

public interface UserInterface {
    User saveUser(User u, Long roleId);

    User getUserById(Long userId);

    User updateUser(UserDTO userDTO);

    List<User> getAllUsers();
}
