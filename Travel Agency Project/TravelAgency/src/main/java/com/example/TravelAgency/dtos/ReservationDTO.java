package com.example.TravelAgency.dtos;

import com.example.TravelAgency.model.Hotel;
import com.example.TravelAgency.model.MealPlan;
import com.example.TravelAgency.model.User;
import lombok.Data;

import java.time.LocalDate;
@Data
public class ReservationDTO {
    private Long reservationId;
    private Integer numberOfRooms;
    private Integer numberOfPersons;
    private Double price;
    private MealPlan mealPlan;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double extraPrice;
}
