package com.ZhyMuseumProject.ZhyMuseum.Service.Imp;


import com.ZhyMuseumProject.ZhyMuseum.Repository.UserRepository;
import com.ZhyMuseumProject.ZhyMuseum.Service.interfaces.UserServices;
import com.ZhyMuseumProject.ZhyMuseum.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserImp implements UserServices {

    // Autowired UserRepository for database operations
    @Autowired
     UserRepository userRepository;


    // get all the users
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // get a user by ID
    @Override
    public User findUserById(int id) {
            return userRepository.findById(id).get();
        }


   // add a new user
    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    // delete a users
    @Override
    public void deleteUser(Integer id) {
    userRepository.deleteById(id);

    }


    // Update a user to the database and check if the user not found give a massage
    @Override
    public String updateUser(int id, User user) {
        User UserFound = userRepository.findById(id).get();
        try {
            if (UserFound != null) {

                UserFound.setUsername(user.getUsername());

                userRepository.save(UserFound);
                return "User Details Updated";
            } else {
                return "User with user id " + id + " not found";
            }
        } catch (Exception e) {
            return "Users Not Updated";
        }
    }


        @Override
        public UserDetailsService userDetailsService() {
            return new UserDetailsService() {
                @Override
                public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                    UserDetails userDetails = userRepository.findByUsername(username);
                    if(userDetails == null){
                        throw new UsernameNotFoundException("User not found");
                    }
                    return userDetails;
                }
            };
        }

    }
