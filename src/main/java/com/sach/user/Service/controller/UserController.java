package com.sach.user.Service.controller;

import com.sach.user.Service.FeignClient.RatingService;
import com.sach.user.Service.enties.Rating;
import com.sach.user.Service.enties.User;
import com.sach.user.Service.service.UserServie;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserServie userServie;

    @Autowired
    RatingService ratingService;

    //Create
    @PostMapping
    public ResponseEntity<User>  createUser(@RequestBody User user){
      User saveUser  =userServie.SaveUser(user);
      return ResponseEntity.status(HttpStatus.CREATED).body(saveUser);
    }

    //get All USer
    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> getAllUsers(){

        List<User> userList=userServie.getAllUsers();
        return ResponseEntity.ok(userList);

    }
    // Get Single User
    @CircuitBreaker(name="Hotel_Rating_Breaker",fallbackMethod = "RatingHotelFallBack")
    @GetMapping("/{Id}")
    public ResponseEntity<User> getSingleUsers(@PathVariable int Id){
        User user=userServie.getUser(Id);
        return ResponseEntity.ok(user);

    }

    //Fallback Method for Hotel and Rating
    public ResponseEntity<User> RatingHotelFallBack(int Id,Exception ex){
          System.out.println("Fallback executed Because Rating or Hotel services may  fail"+ex.getMessage());
          User user= User.builder()
                  .email("ss@gamil.com")
                  .userName("username")
                  .UserLastName("LastNAme")
                  .address("pune")
                  .mobileNo("888909")
                  .userId(1).build();
              return new ResponseEntity<>(user,HttpStatus.OK);

    }
    int retry=1;
    @GetMapping("/feign/{Id}")
    @Retry(name="RetryHotelRating", fallbackMethod = "RatingHotelFallBack")
    public ResponseEntity<User> getUser(@PathVariable int Id){
        System.out.println("Retry count "+retry);
        retry++;

        User user=userServie.getUserByFeignClient(Id);
        return ResponseEntity.ok(user);

    }
    // Call Feign Client Direct from controller to Other service
    int retryCount=1;
    @GetMapping("/Allfeign/{Id}")
    @Retry(name="RetryHotelRating", fallbackMethod = "RatingHotelFallBack")
    public ResponseEntity<List<Rating>> getFiegnAllUser(@PathVariable int Id) {
        System.out.println("Retry count " + retry);
        retryCount++;
        System.out.println("Rating List " + Calendar.getInstance());
        List<Rating> userList = (List<Rating>) ratingService.getAllRating();
        System.out.println("Rating List " + userList);
        return ResponseEntity.ok(userList);
    }


    // Get Delete User By ID
    @DeleteMapping("/delete/{uesrId}")
    public ResponseEntity<String> deleteUser(@PathVariable int uesrId){
        String msg=userServie.deleteUser(uesrId);
        return ResponseEntity.ok(msg);

    }
    @PostMapping("/update")
    public ResponseEntity<User>  Update(@RequestBody User user){
        System.out.println("User Updated Controller");
        User updateUser  =userServie.updateUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(updateUser);
    }

}
