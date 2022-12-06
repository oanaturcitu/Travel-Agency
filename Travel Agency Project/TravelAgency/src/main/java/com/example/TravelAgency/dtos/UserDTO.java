package com.example.TravelAgency.dtos;

import com.example.TravelAgency.model.Discount;
import lombok.Data;

@Data
public class UserDTO {
    private Long userId;
    private String username;
    private String address;
    private String phone;
    private String email;
    private String password;
}
