package com.ZhyMuseumProject.ZhyMuseum.Service.Imp;

import com.ZhyMuseumProject.ZhyMuseum.Repository.ArtworkRepository;
import com.ZhyMuseumProject.ZhyMuseum.Service.interfaces.ArtworkService;
import com.ZhyMuseumProject.ZhyMuseum.entity.Artwork;
import com.ZhyMuseumProject.ZhyMuseum.entity.ArtworkType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtworkServiceImpl implements ArtworkService {

    @Autowired
    private ArtworkRepository artworkRepository;

    @Override
    public List<Artwork> getAllArtworks() {
        return artworkRepository.findAll();
    }

    @Override
    public Optional<Artwork> getArtworkById(String artworkId) {
        return artworkRepository.findByArtworkId(artworkId);
    }

    @Override
    public List<Artwork> getArtworksByType(ArtworkType artworkType) {
        return artworkRepository.findByArtworkType(artworkType);
    }
    @Override
    public Artwork addArtwork(Artwork artwork) {
        return artworkRepository.save(artwork);
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

