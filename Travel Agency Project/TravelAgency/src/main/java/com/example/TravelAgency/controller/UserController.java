package com.example.TravelAgency.controller;

import com.example.TravelAgency.dtos.UserDTO;
import com.example.TravelAgency.model.User;
import com.example.TravelAgency.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save/{roleId}")
    public User saveUser(@RequestBody User user, @PathVariable("roleId") Long roleId){
        return userService.saveUser(user, roleId);
    }

    @GetMapping("/get/{userId}")
    public User getUserById(@PathVariable("userId") Long userId){
        return userService.getUserById(userId);
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody UserDTO userDTO){
        return userService.updateUser(userDTO);
    }

    @GetMapping("get/all")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

}
