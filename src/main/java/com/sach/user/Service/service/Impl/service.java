package com.sach.user.Service.service.Impl;

import com.sach.user.Service.enties.User;
import com.sach.user.Service.exception.ResourseNotFoundException;
import com.sach.user.Service.repositories.UserRepo;
import com.sach.user.Service.service.UserServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class service implements UserServie {

    @Autowired
    UserRepo userRepo;
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
        return userRepo.findById(userId).orElseThrow(()->new ResourseNotFoundException("Resource With Given Id Is not available"+userId));
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
