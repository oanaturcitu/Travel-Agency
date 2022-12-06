package com.example.TravelAgency.repository;

import com.example.TravelAgency.model.Reservation;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    Set<Reservation> findByUserUserId(Long userId);
    Set<Reservation> findByHotelHotelId(Long hotelId);
}
