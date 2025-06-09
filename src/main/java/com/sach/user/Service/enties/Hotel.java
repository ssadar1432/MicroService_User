package com.sach.user.Service.enties;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hotel {
    private int hotelId;

    private String hotelName;

    private String location;
      private String about;
}
