package com.ZhyMuseumProject.ZhyMuseum.Controller;


import com.ZhyMuseumProject.ZhyMuseum.Repository.UserRepository;
import com.ZhyMuseumProject.ZhyMuseum.Service.Imp.UserImp;
import com.ZhyMuseumProject.ZhyMuseum.entity.User;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@NoArgsConstructor
public class UserController {

    @Autowired
    private UserImp userImp;


    @Autowired
    private UserRepository userRepository;


     // Get a list of all users
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        return userImp.getAllUsers();
    }

    // Get a user by id
    @GetMapping(value = "/users/{id}")
    public User findUserById(@PathVariable int id){
        return userImp.findUserById(id);
    }

    //add a new user
    @PostMapping("/users/add")
    public ResponseEntity<String> addUsers(@RequestBody @Valid User user) {
        try {
            userImp.addUser(user);
            String massage = "User add successfully";
            return ResponseEntity.status(HttpStatus.OK).body(massage);
        } catch (Exception e) {
            String errorMassage = "User not found" + e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMassage);
        }
    }



    // Delete user with handling Exception
    @DeleteMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        Optional<User> UserFound = userRepository.findById(id);
        try {
            if (UserFound.isPresent()) {
                userImp.deleteUser(id);
                return "User with ID " + id +" deleted";
            } else {
                return "User with  ID " + id + " not found";
            }
        } catch (Exception e) {
            return "User does not deleted";
        }
    }


    // Update a user
    @PutMapping("/users/update/{id}")
    public String updateUser(@PathVariable int id, @RequestBody User user){
        return userImp.updateUser(id,user);
    }

}
