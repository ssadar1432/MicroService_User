package com.sach.user.Service.repositories;

import com.sach.user.Service.enties.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Integer > {


    @Query("select us FROM User us WHERE us.userName =:name")
    public User getUserByName(String name);
}
