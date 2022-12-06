package com.example.TravelAgency.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    @ManyToOne
    private User user;

    @ManyToOne
    private Hotel hotel;

    private Integer numberOfRooms;
    private int numberOfPersons;
    private double price;
    @Enumerated(value=EnumType.STRING)
    private MealPlan mealPlan;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate reservationDate;
    private double extraPrice;
    @Enumerated(value=EnumType.STRING)
    private Currency currency;

}
