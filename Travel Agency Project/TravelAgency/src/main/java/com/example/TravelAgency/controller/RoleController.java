package com.example.TravelAgency.controller;

import com.example.TravelAgency.model.Role;
import com.example.TravelAgency.model.User;
import com.example.TravelAgency.service.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/role/")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/get/{roleId}")
    public Role getRolebyId(@PathVariable("roleId")Long roleId){
        return roleService.getRoleById(roleId);
    }

    @GetMapping("get/all")
    public List<Role> getAllRols(){
        return roleService.getAllRols();
    }
}
