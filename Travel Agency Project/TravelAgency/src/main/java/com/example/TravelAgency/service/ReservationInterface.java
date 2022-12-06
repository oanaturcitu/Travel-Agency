package com.example.TravelAgency.service;

import com.example.TravelAgency.dtos.ReservationDTO;
import com.example.TravelAgency.model.Reservation;

import java.util.Set;

public interface ReservationInterface {
    Reservation createReservation(Reservation reservation, Long userId, Long hotelId);
    Reservation updateReservation(ReservationDTO reservationDTO);
    void deleteReservationById(Long reservationId);

    Set<Reservation> getReservationByUserId(Long userId);

    Double getTotalPriceForUser(Long userId);

    Set<Reservation> getReservationByHotelId(Long hotelId);
}
