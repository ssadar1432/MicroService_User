package com.sach.user.Service.controller;

import com.sach.user.Service.enties.User;
import com.sach.user.Service.service.UserServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserServie userServie;

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
    @GetMapping("/{Id}")
    public ResponseEntity<User> getSingleUsers(@PathVariable int Id){
        User user=userServie.getUser(Id);
        return ResponseEntity.ok(user);

    }
    @GetMapping("/feign/{Id}")
    public ResponseEntity<User> getUser(@PathVariable int Id){
        User user=userServie.getUserByFeignClient(Id);
        return ResponseEntity.ok(user);

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
