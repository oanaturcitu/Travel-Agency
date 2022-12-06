package com.example.TravelAgency.controller;

import com.example.TravelAgency.model.Hotel;
import com.example.TravelAgency.model.User;
import com.example.TravelAgency.service.HotelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/hotel/")
public class HotelController {
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping("/save")
    public Hotel createHotel(@RequestBody Hotel hotel){
        return hotelService.createHotel(hotel);
    }

    @GetMapping("get/all")
    public ResponseEntity<Set<Hotel>> getAllHotels(){
        return ResponseEntity.ok (hotelService.getAllHotels());
    }

    @GetMapping("/get/{hotelId}")
    public Hotel getHotelById(@PathVariable("hotelId") Long hotelId){
        return hotelService.getHotelById(hotelId);
    }

    //@PostMapping("/uploadPhoto/{hotelId}")
}
