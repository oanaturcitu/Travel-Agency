package com.example.TravelAgency.service;

import com.example.TravelAgency.dtos.UserDTO;
import com.example.TravelAgency.exceptions.NotFound;
import com.example.TravelAgency.model.Discount;
import com.example.TravelAgency.model.Role;
import com.example.TravelAgency.model.User;
import com.example.TravelAgency.repository.RoleRepository;
import com.example.TravelAgency.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService implements UserInterface{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    public User saveUser(User u, Long roleId) {
        User user = new User();
        Optional <Role> role = roleRepository.findById(roleId);
        if(role.isEmpty()){
            throw new NotFound("Role not found!");
        }
        Role role1 = role.get();
        user.setRole(role1);
        if(verifyCNP(u.getCnp())){
            user.setCnp(u.getCnp());
        }else{
            throw new NotFound("Invalid CNP!");
        }
        if(verifyEmail(u.getEmail())){
            user.setEmail(u.getEmail());
        }else{
            throw new NotFound("Invalid email address!");
        }
        user.setPassword(generatePassword());
        user.setUsername(u.getUsername());
        user.setAddress(u.getAddress());
        user.setPhone(u.getPhone());
        user.setDiscount(u.getDiscount());
        user.setGender(u.getGender());

        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        }
        throw new NotFound("User id not found");
    }

    @Override
    public User updateUser(UserDTO userDTO) {
        User user = getUserById(userDTO.getUserId());
        if (userDTO.getUsername() != null) {
            user.setUsername(userDTO.getUsername());
        }
        if (userDTO.getAddress() != null) {
            user.setAddress(userDTO.getAddress());
        }
        if (userDTO.getPhone() != null) {
        user.setPhone(userDTO.getPhone());
        }
        if(userDTO.getEmail()!=null) {
            if(verifyEmail(userDTO.getEmail())){
                user.setEmail(userDTO.getEmail());
            } else{
                throw new RuntimeException("Invalid email address");
            }
        }
        if(userDTO.getPassword()!=null) {
            user.setPassword(userDTO.getPassword());
        }

        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
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

    public static boolean verifyEmail(String emailAddress) {
        String emailPattern = "^[\\w.+\\-]+@gmail\\.com$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(emailAddress);
        return matcher.matches();
    }

    public static boolean verifyCNP(String cnp) {
        if (cnp.length() != 13 || !hasDigits(cnp) ||  !verifyFormatMonth(cnp) || !verifyFormatYear(cnp)) {
            return false;
        }
        return true;
    }
    private static boolean verifyFormatYear(String cnp){
        if (cnp.charAt(0) == '1' || cnp.charAt(0) == '2') {
            String year = cnp.substring(1, 3);
            int yearAsInt = Integer.parseInt(year);
            return yearAsInt >= 0 && yearAsInt <= 99;}
        else if (cnp.charAt(0) == '5' || cnp.charAt(0) == '6') {
            String year = cnp.substring(1, 3);
            int yearAsInt = Integer.parseInt(year);
            return yearAsInt >= 0 && yearAsInt <= 21; }
        return false;
    }
    private static boolean verifyFormatMonth(String cnp) {
        String year = cnp.substring(1,3);
        int yearAsInt = Integer.parseInt(year);
        String month = cnp.substring(3,5);
        int monthAsInt = Integer.parseInt(month);
        String day= cnp.substring(5,7);
        int dayAsInt = Integer.parseInt(day);
        if(monthAsInt <1 || monthAsInt >12){
            return false;}
        else {
            if(monthAsInt == 1 || monthAsInt == 3 || monthAsInt == 5 || monthAsInt == 7 ||
                    monthAsInt == 8 || monthAsInt == 10 || monthAsInt == 12){
                return  dayAsInt >= 1 && dayAsInt <=31;}
            else if (monthAsInt == 4 || monthAsInt == 6 || monthAsInt == 9 || monthAsInt == 11){
                return  dayAsInt >= 1 && dayAsInt <=30;}
            else {
                if (yearAsInt%4 == 0){
                    return dayAsInt >=1 && dayAsInt <= 29;}
                else {
                    return dayAsInt >=1 && dayAsInt <= 28;}
            }
        }
    }
    private static boolean hasDigits(String cnp) {
        for (int i = 0; i < cnp.length(); i++) {
            if (!Character.isDigit(cnp.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
