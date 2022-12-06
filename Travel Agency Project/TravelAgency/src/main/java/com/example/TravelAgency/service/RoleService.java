package com.example.TravelAgency.service;

import com.example.TravelAgency.model.Role;
import com.example.TravelAgency.model.User;
import com.example.TravelAgency.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements RoleInterface{
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleById(Long roleId) {
        return roleRepository.findById(roleId).get();
    }

    @Override
    public List<Role> getAllRols() {
        return (List<Role>) roleRepository.findAll();
    }
}
