package com.ZhyMuseumProject.ZhyMuseum.Service.interfaces;

import com.ZhyMuseumProject.ZhyMuseum.entity.Artwork;
import com.ZhyMuseumProject.ZhyMuseum.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;

/*

  The UserService is an interface that defines the methods that are available to perform operations on User entities.

 */

public interface UserService {

    /*

     List of methods to show all users, add , delete and update Users.

    */

    public List<User> getAllUsers();

    User findUserById(int id);

    public User addUser(User user);

    public void deleteUser(Integer id);

    public String updateUser(int id, User user);

    public void addArtwork(Artwork artwork);

    public String updateArtwork(String artworkId, Artwork artwork);
    public String partialUpdateArtwork(String artworkId, Map<String, Object> updatedArtwork);

    public String deleteArtwork(String artworkId);

    public void viewViewersCounter();

    public UserDetailsService userDetailsService();
}
