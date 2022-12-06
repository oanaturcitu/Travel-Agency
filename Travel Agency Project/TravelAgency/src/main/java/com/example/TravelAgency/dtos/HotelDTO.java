package com.example.TravelAgency.dtos;

import com.example.TravelAgency.model.MealPlan;

public class HotelDTO {
    private Long hotelId;
    private byte[] photo;
    private String town;
    private int numberOfRooms;
    private MealPlan mealPlan;
    private Double price;
    private boolean spa;
    private boolean gym;
    private boolean transferHotelToAirport;
}
