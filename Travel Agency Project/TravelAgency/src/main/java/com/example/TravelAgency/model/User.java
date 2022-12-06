package com.example.TravelAgency.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

//@Table(name = "users")
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

   // @OneToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ManyToOne
    private Role role;

//    @OneToMany (cascade = CascadeType.ALL, mappedBy = "user")
//    private Set<Reservation> reservations;

    private String username;
    private String password;
    private String cnp;
    private String address;
    private String phone;
    private String email;
    @Enumerated(value=EnumType.STRING)
    private Gender gender;
    @Enumerated(value=EnumType.STRING)
    private Discount discount;

}
