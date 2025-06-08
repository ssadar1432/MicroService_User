package com.sach.user.Service.payloads;

import lombok.*;
import org.springframework.http.HttpStatus;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResourse {

    private String Message;
    private boolean sucess;

    private HttpStatus status;


}
