package com.ZhyMuseumProject.ZhyMuseum.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SignUpRequest {
    private String username;
    private String password;
    private String email;


}
