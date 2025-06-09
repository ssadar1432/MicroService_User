package com.sach.user.Service.enties;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rating {

    private int ratingId;
    private int userId;
    private int hotelId;
    private String feedback;
    private int rating;
    private Hotel hotel;
}
