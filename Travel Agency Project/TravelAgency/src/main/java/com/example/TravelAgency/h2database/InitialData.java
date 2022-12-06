package com.example.TravelAgency.h2database;

import com.example.TravelAgency.model.*;
import com.example.TravelAgency.repository.HotelRepository;
import com.example.TravelAgency.repository.RoleRepository;
import com.example.TravelAgency.repository.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class InitialData implements ApplicationListener<ContextRefreshedEvent> {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final HotelRepository hotelRepository;

    Role admin = new Role();
    Role travelAgent = new Role();
    Role client = new Role();
    Role hotelEmployee = new Role();

    public InitialData(RoleRepository roleRepository, UserRepository userRepository, HotelRepository hotelRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.hotelRepository = hotelRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        roleRepository.saveAll(addRolls());
        userRepository.saveAll(addUsers());
        hotelRepository.saveAll(addHotels());
    }

    public List<Role> addRolls(){
        List<Role> roles = new ArrayList<>();

        admin.setRoleType(RoleType.ADMIN);
        travelAgent.setRoleType(RoleType.TRAVEL_AGENT);
        client.setRoleType(RoleType.CLIENT);
        hotelEmployee.setRoleType(RoleType.HOTEL_EMPLOYEE);

        roles.add(admin);
        roles.add(travelAgent);
        roles.add(client);
        roles.add(hotelEmployee);

        return roles;
    }

    public List<User> addUsers(){

        List<User> users = new ArrayList<>();

        User adrian = new User();
        User oana = new User();
        User cristina = new User();
        User miruna = new User();

        adrian.setRole(admin);
        adrian.setUsername("Adrian");
        adrian.setPassword(generatePassword());
        adrian.setCnp("1211310345345");
        adrian.setAddress("Timisoara, bld. L. Rebreanu");
        adrian.setPhone("0744478344");
        adrian.setEmail("adrian@gmail.com");
        adrian.setGender(Gender.M);
        adrian.setDiscount(Discount.LEVEL1);

        oana.setRole(travelAgent);
        oana.setUsername("Oana");
        oana.setPassword(generatePassword());
        oana.setCnp("2881212160024");
        oana.setAddress("Craiova, str. Recunostintei");
        oana.setPhone("0711018113");
        oana.setEmail("oana@gmail.com");
        oana.setGender(Gender.F);
        oana.setDiscount(Discount.LEVEL2);

        cristina.setRole(client);
        cristina.setUsername("Cristina");
        cristina.setPassword(generatePassword());
        cristina.setCnp("2900314160934");
        cristina.setAddress("Bucuresti, Calea Victoriei");
        cristina.setPhone("0768321564");
        cristina.setEmail("cristina@gmail.com");
        cristina.setGender(Gender.F);
        cristina.setDiscount(Discount.LEVEL3);

        miruna.setRole(hotelEmployee);
        miruna.setUsername("Miruna");
        miruna.setPassword(generatePassword());
        miruna.setCnp("2950914160987");
        miruna.setAddress("Bucuresti, Str. Teilor");
        miruna.setPhone("0773903756");
        miruna.setEmail("miruna@gmail.com");
        miruna.setGender(Gender.F);
        miruna.setDiscount(Discount.LEVEL3);

        users.add(adrian);
        users.add(oana);
        users.add(cristina);
        users.add(miruna);

        return users;
    }

    public List<Hotel> addHotels(){
        List <Hotel> hotels = new ArrayList<>();
        Hotel continental = new Hotel();
        Hotel ibis = new Hotel();
        Hotel sofitel = new Hotel();

        continental.setHotelName("Continental");
        continental.setTown("Bucuresti");
        continental.setNumberOfRooms(200);
        continental.setMealPlan(MealPlan.ALL_INCLUSIVE);
        continental.setPrice(170.50);
        continental.setSpa(true);
        continental.setGym(true);
        continental.setTransferHotelToAirport(true);

        ibis.setHotelName("Ibis");
        ibis.setTown("Viena");
        ibis.setNumberOfRooms(120);
        ibis.setMealPlan(MealPlan.BREAKFAST);
        ibis.setPrice(130.25);
        ibis.setSpa(false);
        ibis.setGym(true);
        ibis.setTransferHotelToAirport(true);

        sofitel.setHotelName("Sofitel");
        sofitel.setTown("Paris");
        sofitel.setNumberOfRooms(220);
        sofitel.setMealPlan(MealPlan.HALF_BOARD);
        sofitel.setPrice(200.99);
        sofitel.setSpa(true);
        sofitel.setGym(true);
        sofitel.setTransferHotelToAirport(true);

        hotels.add(continental);
        hotels.add(ibis);
        hotels.add(sofitel);

        return hotels;
    }
    public static String generatePassword() {
        String passwordString;
        String upperLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerLetters = "abcdefghijklmnopqrstuvxyz";
        String digits = "0123456789";
        String specialChar = "!@#$%^&*?+";
        String allChars = upperLetters + lowerLetters + digits + specialChar;

        Random random = new Random();
        int length = random.nextInt(6) + 6;
        char[] password = new char[length];

        password[0] = lowerLetters.charAt(random.nextInt(lowerLetters.length()));
        password[1] = upperLetters.charAt(random.nextInt(upperLetters.length()));
        password[2] = specialChar.charAt(random.nextInt(specialChar.length()));
        password[3] = digits.charAt(random.nextInt(digits.length()));

        for (int i = 4; i < length; i++) {
            password[i] = allChars.charAt(random.nextInt(allChars.length()));
        }


        passwordString = String.valueOf(password);
        return passwordString;
    }
}
