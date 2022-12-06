package com.example.TravelAgency.service;

import com.example.TravelAgency.model.Role;
import com.example.TravelAgency.model.User;

import java.util.List;

public interface RoleInterface {
    Role getRoleById(Long roleId);

    List<Role> getAllRols();
}
