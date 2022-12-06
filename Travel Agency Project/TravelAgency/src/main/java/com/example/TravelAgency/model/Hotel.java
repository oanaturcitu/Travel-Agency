package com.example.TravelAgency.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotelId;
    private String hotelName;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hotel")
//    private Reservation reservation;

    @Lob
    private byte[] photo;

    private String town;
    private int numberOfRooms;

    @Enumerated(value= EnumType.STRING)
    private MealPlan mealPlan;

    private Double price;
    private boolean spa;
    private boolean gym;
    private boolean transferHotelToAirport;

}
