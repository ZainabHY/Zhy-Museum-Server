package com.ZhyMuseumProject.ZhyMuseum.Service.Imp;


import com.ZhyMuseumProject.ZhyMuseum.Repository.ArtworkRepository;
import com.ZhyMuseumProject.ZhyMuseum.Repository.UserRepository;
import com.ZhyMuseumProject.ZhyMuseum.Service.interfaces.UserService;
import com.ZhyMuseumProject.ZhyMuseum.entity.Artwork;
import com.ZhyMuseumProject.ZhyMuseum.entity.ArtworkType;
import com.ZhyMuseumProject.ZhyMuseum.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    // Autowired UserRepository for database operations
    @Autowired
    UserRepository userRepository;

    @Autowired
    ArtworkRepository artworkRepository;


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


    @Override
    public void addArtwork(Artwork artwork) {
        artwork.setArtworkId(artwork.customIdGenerator(artwork));
        artworkRepository.save(artwork);
    }

    @Override
    public String updateArtwork(String artworkId, Artwork artwork) {
        Optional<Artwork> foundArtwork = artworkRepository.findById(artworkId);

        // 1. Checking if artwork with ID artworkId is present in DB
        if (foundArtwork.isPresent()) {
            // 2. Getting the found artwork to update it to the same ID
            Artwork existingArtwork = foundArtwork.get();

            // 3. Set the new values
            existingArtwork.setImage(artwork.getImage());
            existingArtwork.setTimeSpent(artwork.getTimeSpent());
            existingArtwork.setDescription(artwork.getDescription());
            existingArtwork.setArtworkType(artwork.getArtworkType());

            // 4. Save the updated artwork to the database
            artworkRepository.save(existingArtwork);
            return "Artwork with Artwork ID: " + artworkId + " updated successfully";
        } else {
            return "Sorry, artwork with Artwork ID: " + artworkId + " not found";
        }
    }

    // Partial update of artwork data
    @Override
    public String partialUpdateArtwork(String artworkId, Map<String, Object> updatedArtwork) {
        Optional<Artwork> foundArtwork = artworkRepository.findById(artworkId);

        // Checking if artwork with ID artworkId is present in the DB
        if (foundArtwork.isPresent()) {
            Artwork existingArtwork = foundArtwork.get();

            for (Map.Entry<String, Object> entry : updatedArtwork.entrySet()) {
                String fieldName = entry.getKey();
                Object fieldValue = entry.getValue();

                // Update the specified field if it exists and the value is not null
                switch (fieldName) {
                    case "image":
                        if (fieldValue != null) {
                            existingArtwork.setImage((String) fieldValue);
                        }
                        break;

                    case "timeSpent":
                        if (fieldValue != null) {
                            existingArtwork.setTimeSpent((String) fieldValue);
                        }
                        break;

                    case "description":
                        if (fieldValue != null) {
                            existingArtwork.setDescription((String) fieldValue);
                        }
                        break;

                    case "artworkType":
                        if (fieldValue != null) {
                            existingArtwork.setArtworkType((ArtworkType) fieldValue);
                        }
                        break;

                    default:
                        throw new IllegalStateException("Unexpected value: " + fieldName);
                }
            }

            // Save the updated artwork to the database
            artworkRepository.save(existingArtwork);
            return "Artwork with Artwork ID: " + artworkId + " updated successfully";
        } else {
            return "Sorry, artwork with Artwork ID: " + artworkId + " not found";
        }
    }


    @Override
    public String deleteArtwork(String artworkId) {
//        artworkRepository.deleteById(artworkId);
        Optional<Artwork> foundArtwork = artworkRepository.findById(artworkId);

        // 1. Checking if artwork with ID artworkId is present int the DB
        if(foundArtwork.isPresent())
        {
            // 2. Delete the artwork from DB
            artworkRepository.deleteById(artworkId);
            return "Artwork with ID: " + artworkId + " deleted successfully";
        }
        else
            return "Sorry, artwork with ID: " + artworkId + " not found";


    }

    @Override
    public void viewViewersCounter() {

    }

    }
