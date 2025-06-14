package com.sach.user.Service.service;

import com.sach.user.Service.enties.User;

import java.util.List;


public interface UserServie {

    //Create
    User SaveUser(User user);

    //get ALl User

    List<User> getAllUsers();

    //get Single user by Id

    User getUser(int userId);

    User getUserByFeignClient(int userId);

    String deleteUser(int userId);

    User updateUser(User user);




}
