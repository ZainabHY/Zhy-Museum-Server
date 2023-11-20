package com.ZhyMuseumProject.ZhyMuseum.DTO;


import com.ZhyMuseumProject.ZhyMuseum.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVerify {
    private int id;
    private String username;
    private Role roles;
}
