package com.example.TravelAgency.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    //@OneToMany (cascade = CascadeType.ALL)
    //@JoinColumn(name = "role_u")
    //private Set<User> users;

    @Enumerated(value= EnumType.STRING)
    private RoleType roleType;


}
