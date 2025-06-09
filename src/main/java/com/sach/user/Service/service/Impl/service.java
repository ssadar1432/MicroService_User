package com.sach.user.Service.service.Impl;

import com.sach.user.Service.enties.Hotel;
import com.sach.user.Service.enties.Rating;
import com.sach.user.Service.enties.User;
import com.sach.user.Service.exception.ResourseNotFoundException;
import com.sach.user.Service.repositories.UserRepo;
import com.sach.user.Service.service.UserServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class service implements UserServie {

    @Autowired
    UserRepo userRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public User SaveUser(User user) {

        //Generate Unique UserId
        return userRepo.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUser(int userId) {
   User user= userRepo.findById(userId).orElseThrow(()->new ResourseNotFoundException("Resource With Given Id Is not available"+userId));

        //http://localhost:8083/rating/users/1

       Rating[] userRatingEntries =restTemplate.getForObject("http://localhost:8083/rating/users/"+user.getUserId(), Rating[].class);

       List<Rating>  userRating= Arrays.stream(userRatingEntries).toList();
              List<Rating> ratingList=userRating.stream().map(rating ->
                 {
                     //http://localhost:8082/hotel/2
                     ResponseEntity<Hotel> htlEntry = restTemplate.getForEntity("http://localhost:8082/hotel/"+rating.getHotelId(), Hotel.class);
             Hotel htl =htlEntry.getBody();
              rating.setHotel(htl);
                return rating;
                 }

                 ).collect(Collectors.toList());

        user.setRating(ratingList);
        return user;
    }

    @Override
    public String deleteUser(int userId) {
        if (userRepo.findById(userId).isPresent()) {
            userRepo.deleteById(userId);
            return "User deleted successfully";
        }
        return "No such User in the database";
    }

    @Override
    public User updateUser(User user) {
        System.out.println("User Updated Service");
        User us=userRepo.getUserByName(user.getUserName());
       if(us.getUserName().equalsIgnoreCase(user.getUserName())){
           System.out.println("User Updated Condition check");
           us.setUserName(user.getUserName());
           us.setUserLastName(user.getUserLastName());
           us.setEmail(user.getEmail());
           us.setMobileNo(user.getMobileNo());
           us.setAddress(user.getAddress());
           us.setUserId(us.getUserId());
           userRepo.save(us);
           System.out.println("User Updated");

       }
        return us;

    }
}
