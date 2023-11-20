package com.ZhyMuseumProject.ZhyMuseum.Service.interfaces;

import com.ZhyMuseumProject.ZhyMuseum.entity.Artwork;

import java.util.List;

/*

  The ArtworkService is an interface that defines the methods that are available to perform operations on User entities.

 */

public interface ArtworkService {

    /*

     List of methods to show all Artworks, add , delete and update.

    */
    public List<Artwork> getAllArtworks();

    public Artwork addArtwork(Artwork book);

    public void deleteArtwork(String id);

    public String updateArtwork(String id, Artwork book);
}
