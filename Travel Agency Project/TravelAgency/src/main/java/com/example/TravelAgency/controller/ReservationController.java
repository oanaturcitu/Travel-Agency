package com.example.TravelAgency.controller;

import com.example.TravelAgency.dtos.ReservationDTO;
import com.example.TravelAgency.dtos.UserDTO;
import com.example.TravelAgency.model.Hotel;
import com.example.TravelAgency.model.Reservation;
import com.example.TravelAgency.model.User;
import com.example.TravelAgency.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/reservation/")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
    @PostMapping("/save/{userId}/{hotelId}")
    public Reservation createReservation(@RequestBody Reservation reservation,
                                         @PathVariable("userId") Long userId,
                                         @PathVariable("hotelId") Long hotelId){
        return reservationService.createReservation(reservation, userId, hotelId);
    }
    @PutMapping("/update")
    public Reservation updateReservation(@RequestBody ReservationDTO reservationDTO){
        return reservationService.updateReservation(reservationDTO);
    }
    @DeleteMapping(path = "delete/{reservationId}")
    public ResponseEntity<?> deleteReservationById(@PathVariable Long reservationId){
        reservationService.deleteReservationById(reservationId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("get/user/{userId}")
    public ResponseEntity<Set<Reservation>> getReservationByUserId(@PathVariable("userId")Long userId){
        return ResponseEntity.ok (reservationService.getReservationByUserId(userId));
    }

    @GetMapping("get/price/{userId}")
    public Double getTotalPriceForUser(@PathVariable("userId")Long userId){
        return reservationService.getTotalPriceForUser(userId);
    }

    @GetMapping("get/hotel/{hotelId}")
    public ResponseEntity<Set<Reservation>> getReservationByHotelId
            (@PathVariable("hotelId")Long hotelId){
        return ResponseEntity.ok
                (reservationService.getReservationByUserId(hotelId));
    }
}

