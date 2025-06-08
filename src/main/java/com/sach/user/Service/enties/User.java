package com.sach.user.Service.enties;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="micro_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int userId;
    @Column(name="NAME")
    private String userName;
    @Column(name="LAST_NAME")
    private String UserLastName;
    @Column(name="EMAIL")
    private  String email;
    @Column(name="MOBILE_NO")
    private String mobileNo;
    @Column(name="ADDRESS")
    private String address;
    @Transient
    private List<Rating> rating;

}
