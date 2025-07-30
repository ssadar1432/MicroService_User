package com.sach.user.Service.FeignClient;

import com.sach.user.Service.enties.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="RATINGSERVICE")
public interface RatingService {


    @GetMapping("/rating/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingByUserID(@PathVariable int userId);

    @GetMapping("/rating/getAllRating")
    public ResponseEntity<List<Rating>> getAllRating();
}
