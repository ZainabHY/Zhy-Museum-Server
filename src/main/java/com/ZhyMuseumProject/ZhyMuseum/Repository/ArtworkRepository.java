package com.ZhyMuseumProject.ZhyMuseum.Repository;

import com.ZhyMuseumProject.ZhyMuseum.entity.Artwork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtworkRepository extends JpaRepository<Artwork,String> {
}
