package com.example.TravelAgency.repository;

import com.example.TravelAgency.model.Hotel;
import org.springframework.data.repository.CrudRepository;

public interface HotelRepository extends CrudRepository<Hotel, Long> {
}
