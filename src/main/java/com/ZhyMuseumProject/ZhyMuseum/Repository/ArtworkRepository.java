package com.ZhyMuseumProject.ZhyMuseum.Repository;

import com.ZhyMuseumProject.ZhyMuseum.entity.Artwork;
import com.ZhyMuseumProject.ZhyMuseum.entity.ArtworkType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtworkRepository extends JpaRepository<Artwork,String> {
    Optional<Artwork> findByArtworkId(String artworkId);
    List<Artwork> findByArtworkType(ArtworkType artworkType);
}
