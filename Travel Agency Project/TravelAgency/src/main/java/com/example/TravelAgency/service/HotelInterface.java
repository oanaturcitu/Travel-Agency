package com.example.TravelAgency.service;

import com.example.TravelAgency.model.Hotel;
import java.util.Set;

public interface HotelInterface {
    Hotel createHotel(Hotel hotel);

    Set<Hotel> getAllHotels();

    Hotel getHotelById(Long hotelId);
}
