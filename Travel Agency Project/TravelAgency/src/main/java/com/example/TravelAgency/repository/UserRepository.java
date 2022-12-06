package com.example.TravelAgency.repository;

import com.example.TravelAgency.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
