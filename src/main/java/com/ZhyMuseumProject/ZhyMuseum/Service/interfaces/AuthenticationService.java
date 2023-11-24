package com.ZhyMuseumProject.ZhyMuseum.Service.interfaces;


import com.ZhyMuseumProject.ZhyMuseum.DTO.JwtAuthenticationRequest;
import com.ZhyMuseumProject.ZhyMuseum.DTO.LoginRequest;
import com.ZhyMuseumProject.ZhyMuseum.DTO.SignUpRequest;
import com.ZhyMuseumProject.ZhyMuseum.entity.User;

public interface AuthenticationService {
    public User signUp(SignUpRequest signUpRequest);
    public JwtAuthenticationRequest login(LoginRequest signInRequest);
}
