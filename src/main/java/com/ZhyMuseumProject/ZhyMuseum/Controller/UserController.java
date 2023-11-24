package com.ZhyMuseumProject.ZhyMuseum.Controller;


import com.ZhyMuseumProject.ZhyMuseum.Repository.UserRepository;
import com.ZhyMuseumProject.ZhyMuseum.Service.Imp.ArtworkServiceImpl;
import com.ZhyMuseumProject.ZhyMuseum.Service.Imp.UserServiceImpl;
import com.ZhyMuseumProject.ZhyMuseum.entity.Artwork;
import com.ZhyMuseumProject.ZhyMuseum.entity.ArtworkType;
import com.ZhyMuseumProject.ZhyMuseum.entity.User;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@NoArgsConstructor
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserServiceImpl userService;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArtworkServiceImpl artworkService;


    // Get a list of all users
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /*
    Artist endpoints
     */
    // Get a user by id
    @GetMapping(value = "/users/{id}")
    @PreAuthorize("hasAuthority('ARTIST')")
    public User findUserById(@PathVariable int id){
        return userService.findUserById(id);
    }

    //add a new user
//    @PostMapping("/users/add")
//    public ResponseEntity<String> addUsers(@RequestBody @Valid User user) {
//        try {
//            userService.addUser(user);
//            String massage = "User add successfully";
//            return ResponseEntity.status(HttpStatus.OK).body(massage);
//        } catch (Exception e) {
//            String errorMassage = "User not found" + e.getMessage();
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMassage);
//        }
//    }

    // Delete user with handling Exception
//    @DeleteMapping("/users/delete/{id}")
//    public String deleteUser(@PathVariable int id) {
//        Optional<User> UserFound = userRepository.findById(id);
//        try {
//            if (UserFound.isPresent()) {
//                userService.deleteUser(id);
//                return "User with ID " + id +" deleted";
//            } else {
//                return "User with  ID " + id + " not found";
//            }
//        } catch (Exception e) {
//            return "User does not deleted";
//        }
//    }

    // Update a user
//    @PutMapping("/users/update/{id}")
//    public String updateUser(@PathVariable int id, @RequestBody User user){
//        return userService.updateUser(id,user);
//    }

//    ===========================================================

    @PostMapping("/artist/addArtwork")
    @PreAuthorize("hasAuthority('ARTIST')")
    public ResponseEntity<String> addArtwork(@RequestBody Artwork artwork){
        try {
//            // Save the artist first
//            User savedArtist = userService.saveArtist(artwork.getArtist());
//            // Set the saved artist to the artwork
//            artwork.setArtist(savedArtist);

            userService.addArtwork(artwork);
            String msg = "Provided artwork with ID: " + artwork.getArtworkId() + " is added successfully";
            return ResponseEntity.status(HttpStatus.OK).body(msg);
        }
        catch (Exception e)
        {
            String errMsg = "Provided artwork with ID: " + artwork.getArtworkId() + " is not added \n\n" + e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errMsg);
        }
    }

    @PutMapping("/artist/updateArtwork/{artworkId}")
    @PreAuthorize("hasAuthority('ROLE_ARTIST')")
    public String updateArtwork(@PathVariable String artworkId, @RequestBody Artwork artwork){
        return userService.updateArtwork(artworkId, artwork);
    }

    // --> Just updating the needed properties
    @PatchMapping("/artist/partialUpdateArtwork/{artworkId}")
    @PreAuthorize("hasAuthority('ROLE_ARTIST')")
    @ResponseStatus(value = HttpStatus.OK)
    public String  partialUpdateArtwork(@PathVariable String artworkId, @RequestBody Map<String, Object> updatedArtwork)
    {
        return userService.partialUpdateArtwork(artworkId, updatedArtwork);
    }

    @DeleteMapping("/artist/deleteArtwork/{artworkId}")
    @PreAuthorize("hasAuthority('ROLE_ARTIST')")
    public String deleteArtwork(@PathVariable String artworkId){
        return userService.deleteArtwork(artworkId);
    }


    /*
     Artist & Artlover endpoints
     */
    @GetMapping("/both/getAllArtworks")
    @PreAuthorize("hasAnyAuthority('ROLE_ARTIST', 'ROLE_ARTLOVER')")
    public List<Artwork> getAllArtworks() {
        return artworkService.getAllArtworks();
    }

    @GetMapping("/both/getArtworkById/{artworkId}")
    @PreAuthorize("hasAnyAuthority('ROLE_ARTIST', 'ROLE_ARTLOVER')")
    public Optional<Artwork> getArtworkById(@PathVariable String artworkId) {
        return artworkService.getArtworkById(artworkId);
    }

    // Getting list of artworks whether by Charcoal or by Pencil
    @GetMapping("/both/getArtworksByType")
    @PreAuthorize("hasAnyAuthority('ROLE_ARTIST', 'ROLE_ARTLOVER')")
    public List<Artwork> getArtworksByType(@RequestParam ArtworkType artworkType) {
        return artworkService.getArtworksByType(artworkType);
    }

}
