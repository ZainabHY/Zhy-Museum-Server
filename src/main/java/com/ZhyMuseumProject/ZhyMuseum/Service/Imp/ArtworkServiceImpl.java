package com.ZhyMuseumProject.ZhyMuseum.Service.Imp;

import com.ZhyMuseumProject.ZhyMuseum.Repository.ArtworkRepository;
import com.ZhyMuseumProject.ZhyMuseum.Service.interfaces.ArtworkService;
import com.ZhyMuseumProject.ZhyMuseum.entity.Artwork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtworkServiceImpl implements ArtworkService {

    @Autowired
    private ArtworkRepository artworkRepository;

    @Override
    public List<Artwork> getAllArtworks() {
        return artworkRepository.findAll();
    }

    @Override
    public Artwork addArtwork(Artwork book) {
        return artworkRepository.save(book);
    }

    @Override
    public void deleteArtwork(String id) {
        artworkRepository.deleteById(id);
    }

    @Override
    public String updateArtwork(String id, Artwork artwork) {
        Artwork artworkFound = artworkRepository.findById(id).get();
        try {
            if (artworkFound != null) {

                artworkFound.setArtworkId(artwork.customIdGenerator(artwork));
                artworkRepository.save(artworkFound);
                return "Artwork Details Updated";
            } else {
                return "Artwork with user id " + id + " not found";
            }
        } catch (Exception e) {
            return "Not Updated";
        }
    }
 }

