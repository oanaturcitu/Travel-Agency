package com.example.TravelAgency.service;

import com.example.TravelAgency.exceptions.NotFound;
import com.example.TravelAgency.model.Hotel;
import com.example.TravelAgency.model.MealPlan;
import com.example.TravelAgency.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class HotelService implements HotelInterface{

    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public Hotel createHotel(Hotel hotel) {
        Hotel hotel1 = new Hotel();
        hotel1.setHotelName(hotel.getHotelName());
        hotel1.setTown(hotel.getTown());
        hotel1.setNumberOfRooms(hotel.getNumberOfRooms());
        hotel1.setMealPlan(hotel.getMealPlan());
        hotel1.setPrice(hotel.getPrice());
        hotel1.setSpa(hotel.isSpa());
        hotel1.setGym(hotel.isGym());
        hotel1.setTransferHotelToAirport(hotel.isTransferHotelToAirport());
        return hotelRepository.save(hotel1);
    }

    @Override
    public Set<Hotel> getAllHotels() {
        Set<Hotel> hotelSet = new HashSet<>();
        hotelRepository.findAll().iterator().forEachRemaining(hotelSet::add);
        return hotelSet;
    }

    @Override
    public Hotel getHotelById(Long hotelId) {
        Optional<Hotel> hotel = hotelRepository.findById(hotelId);
        if(hotel.isEmpty()){
            throw new NotFound("Hotel not found");
        }
        return hotel.get();
    }


}
