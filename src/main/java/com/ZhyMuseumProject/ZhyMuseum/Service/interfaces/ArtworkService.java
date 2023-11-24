package com.ZhyMuseumProject.ZhyMuseum.Service.interfaces;

import com.ZhyMuseumProject.ZhyMuseum.entity.Artwork;
import com.ZhyMuseumProject.ZhyMuseum.entity.ArtworkType;

import java.util.List;
import java.util.Optional;

/*

  The ArtworkService is an interface that defines the methods that are available to perform operations on User entities.

 */

public interface ArtworkService {

    /*

     List of methods to show all Artworks, add , delete and update

    */
    public List<Artwork> getAllArtworks();
    public Optional<Artwork> getArtworkById(String artworkId);
    public List<Artwork> getArtworksByType(ArtworkType artworkType);


    public Artwork addArtwork(Artwork book);

    public void deleteArtwork(String id);

    public String updateArtwork(String id, Artwork book);
}
