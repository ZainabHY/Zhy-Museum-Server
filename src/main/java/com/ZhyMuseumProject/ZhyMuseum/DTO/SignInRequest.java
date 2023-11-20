package com.ZhyMuseumProject.ZhyMuseum.DTO;

import lombok.Data;

@Data
public class SignInRequest {
    private String username;
    private String password;
    private String email;
}
