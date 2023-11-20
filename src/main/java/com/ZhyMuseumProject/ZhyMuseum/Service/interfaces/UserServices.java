package com.ZhyMuseumProject.ZhyMuseum.Service.interfaces;

import com.ZhyMuseumProject.ZhyMuseum.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/*

  The UserService is an interface that defines the methods that are available to perform operations on User entities.

 */

public interface UserServices {

    /*

     List of methods to show all users, add , delete and update Users.

    */

    public List<User> getAllUsers();

    User findUserById(int id);

    public User addUser(User user);

    public void deleteUser(Integer id);

    public String updateUser(int id, User user);

    public UserDetailsService userDetailsService();
}
