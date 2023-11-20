package com.ZhyMuseumProject.ZhyMuseum.Controller;

import com.ZhyMuseumProject.ZhyMuseum.Repository.ArtworkRepository;
import com.ZhyMuseumProject.ZhyMuseum.Service.Imp.ArtworkServiceImpl;
import com.ZhyMuseumProject.ZhyMuseum.entity.Artwork;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ArtworkController {

    @Autowired
    private ArtworkServiceImpl artworkImp;

    @Autowired
    private ArtworkRepository artworkRepository;


    // Get
    @GetMapping("/artworks")
    @ResponseStatus(HttpStatus.OK)
    public List<Artwork> getAllArtworks() {
        return artworkImp.getAllArtworks();
    }

    // post

    @PostMapping("/artworks/add")
    public ResponseEntity<String> addArtwork(@RequestBody @Valid Artwork artwork) {
        try {
            artworkImp.addArtwork(artwork);
            String massage = "Artwork added successfully";
            return ResponseEntity.status(HttpStatus.OK).body(massage);
        } catch (Exception e) {
            String errorMassage = "Artwork not found" + e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMassage);
        }
    }

    // delete

    @DeleteMapping("/artworks/delete/{id}")
    public String deleteArtwork(@PathVariable String id) {
        Optional<Artwork> UserFound = artworkRepository.findById(id);
        try {
            if (UserFound.isPresent()) {
                artworkImp.deleteArtwork(id);
                return "Artwork with ID " + id + " deleted";
            } else {
                return "Artwork with  id " + id + " not found";
            }
        } catch (Exception e) {
            return "Artwork not deleted";
        }
    }

    @PutMapping("/artworks/update/{id}")
    public String updateArtworks(@PathVariable String id, @RequestBody Artwork artwork){
        return artworkImp.updateArtwork(id,artwork);
    }


}
