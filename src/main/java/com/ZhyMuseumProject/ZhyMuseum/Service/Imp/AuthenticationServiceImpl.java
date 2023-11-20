package com.ZhyMuseumProject.ZhyMuseum.Service.Imp;

import com.ZhyMuseumProject.ZhyMuseum.DTO.JwtAuthenticationRequest;
import com.ZhyMuseumProject.ZhyMuseum.DTO.SignInRequest;
import com.ZhyMuseumProject.ZhyMuseum.DTO.SignUpRequest;
import com.ZhyMuseumProject.ZhyMuseum.Repository.UserRepository;
import com.ZhyMuseumProject.ZhyMuseum.Service.interfaces.AuthenticationService;
import com.ZhyMuseumProject.ZhyMuseum.Service.interfaces.JWTService;
import com.ZhyMuseumProject.ZhyMuseum.entity.Role;
import com.ZhyMuseumProject.ZhyMuseum.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {


    @Autowired
    private UserRepository usersRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;


    @Override
    public User signUp(SignUpRequest signUpRequest) {
        User users = new User();

        users.setUsername(signUpRequest.getUsername());
        users.setEmail(signUpRequest.getEmail());
        users.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        users.setRole(Role.ARTLOVER);
        return usersRepository.save(users);
    }

    @Override
    public JwtAuthenticationRequest signIn(SignInRequest signInRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInRequest.getUsername(), signInRequest.getPassword()));
        var users = usersRepository.findByUsername(signInRequest.getUsername());
        if(users == null){
            throw new UsernameNotFoundException("User not found");
        }
        var token = jwtService.generateToken(users);
        JwtAuthenticationRequest jwtAuthenticationRequest = new JwtAuthenticationRequest();

        jwtAuthenticationRequest.setToken(token);
        return jwtAuthenticationRequest;
    }
}


