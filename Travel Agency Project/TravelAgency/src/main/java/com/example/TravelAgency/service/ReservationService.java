package com.example.TravelAgency.service;

import com.example.TravelAgency.dtos.ReservationDTO;
import com.example.TravelAgency.exceptions.NotFound;
import com.example.TravelAgency.model.*;
import com.example.TravelAgency.repository.HotelRepository;
import com.example.TravelAgency.repository.ReservationRepository;
import com.example.TravelAgency.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ReservationService implements ReservationInterface{
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final HotelRepository hotelRepository;

    public ReservationService(ReservationRepository reservationRepository,
                              UserRepository userRepository, HotelRepository hotelRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.hotelRepository = hotelRepository;
    }

    @Override
    public Reservation createReservation(Reservation reservation, Long userId, Long hotelId) {
        Reservation reservation1 = new Reservation();
        Optional<User> user = userRepository.findById(userId);
        Optional<Hotel> hotel = hotelRepository.findById(hotelId);
        if(user.isEmpty()){
            throw new NotFound("User not found!");
        }
        reservation1.setUser(user.get());
        if(hotel.isEmpty()){
            throw new NotFound("Hotel not found!");
        }
        reservation1.setHotel(hotel.get());
        reservation1.setNumberOfRooms(reservation.getNumberOfRooms());
        reservation1.setNumberOfPersons(reservation.getNumberOfPersons());
                reservation1.setMealPlan(reservation.getMealPlan());
        reservation1.setStartDate(reservation.getStartDate());
        reservation1.setEndDate(reservation.getEndDate());
        reservation1.setCurrency(reservation.getCurrency());
        long nights = ChronoUnit.DAYS.between(reservation.getStartDate(),reservation.getEndDate());
        double price = reservation.getNumberOfRooms()
                * calculatePriceWithDiscount(hotel.get().getPrice(), user.get().getDiscount())
                * nights;
        if(reservation.getCurrency() != null && reservation.getCurrency().equals(Currency.USD)){
            price = price * 1.05;
        }
        if(reservation.getCurrency() != null && reservation.getCurrency().equals(Currency.RON)){
            price = price * 4.93;
        }
        reservation1.setPrice(price);
        reservation1.setReservationDate(reservation.getReservationDate());
        reservation1.setExtraPrice(reservation.getExtraPrice());

        return reservationRepository.save(reservation1);
    }
    @Override
    public Reservation updateReservation(ReservationDTO reservationDTO) {
        Optional <Reservation> reservation = reservationRepository.findById(reservationDTO.getReservationId());
        Reservation reservation1 = reservation.get();
        if(reservation.isEmpty()){
            throw new NotFound("Reservation not found!");
        }
        if(reservationDTO.getNumberOfRooms() != null) {
            reservation1.setNumberOfRooms(reservationDTO.getNumberOfRooms());
        }

        if(reservationDTO.getNumberOfPersons() != null) {
            reservation1.setNumberOfPersons(reservationDTO.getNumberOfPersons());
        }

        if(reservationDTO.getNumberOfRooms() != null) {
            reservation1.setPrice(reservationDTO.getNumberOfRooms() * reservation1.getHotel().getPrice());
        }

        if(reservationDTO.getMealPlan() != null) {
            reservation1.setMealPlan(reservationDTO.getMealPlan());
        }

        if(reservationDTO.getStartDate() != null) {
            reservation1.setStartDate(reservationDTO.getStartDate());
        }

        if(reservationDTO.getEndDate() != null) {
            reservation1.setEndDate(reservationDTO.getEndDate());
        }

        if(reservationDTO.getExtraPrice() != null) {
            reservation1.setExtraPrice(reservationDTO.getExtraPrice());
        }
        return reservationRepository.save(reservation1);
    }
    @Override
    public void deleteReservationById(Long reservationId) {
        Optional<Reservation> reservationDel = reservationRepository.findById(reservationId);

        if(reservationDel.isEmpty()) {
            throw new NotFound("Reservation not found");
        }
        reservationRepository.deleteById(reservationId);
    }

    @Override
    public Set<Reservation> getReservationByUserId(Long userId) {
        Set<Reservation> reservations = new HashSet<>();
        reservationRepository.findByUserUserId(userId).iterator().forEachRemaining(reservations::add);
        return reservations;
    }

    @Override
    public Double getTotalPriceForUser(Long userId) {
        Set<Reservation> reservations = new HashSet<>(getReservationByUserId(userId));
        Double totalPrice = 0.0;
        for(Reservation currentReservation : reservations){
            totalPrice +=currentReservation.getPrice();
        }
        return totalPrice;
    }

    @Override
    public Set<Reservation> getReservationByHotelId(Long hotelId) {
        Set<Reservation> reservations = new HashSet<>();
        reservationRepository.findByHotelHotelId(hotelId).iterator()
                .forEachRemaining(reservations::add);
        return reservations;
    }

    public static double calculatePriceWithDiscount(double hotelPrice, Discount discount){
        double finalPrice = hotelPrice;

        if(discount == Discount.LEVEL1){
            finalPrice = finalPrice * 0.9;
        }else if(discount == Discount.LEVEL2){
            finalPrice = finalPrice * 0.85;
        }else if(discount == Discount.LEVEL3) {
            finalPrice = finalPrice * 0.8;
        }
        return finalPrice;
    }

}
