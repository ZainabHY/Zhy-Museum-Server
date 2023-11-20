package com.ZhyMuseumProject.ZhyMuseum.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tbl_artwork")
@Data
public class Artwork {

    @Id
    private String artworkId;

    private String image;
    private String timeSpent;

    private String description;

    @Enumerated(EnumType.STRING)
    private ArtworkType artworkType;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

//    @OneToOne (mappedBy = "artwork", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Artwork artwork;



    // constructors, getters, setters, equals, hashCode and toString;
    public Artwork() {
    }

    public Artwork(String image, String timeSpent, String description, ArtworkType artworkType, User user) {
        this.artworkId = customIdGenerator(this); // Call the method to generate custom ID
        this.image = image;
        this.timeSpent = timeSpent;
        this.description = description;
        this.artworkType = artworkType;
        this.user = user;
//        this.artwork = artwork;
    }



    // Generate custom ID for artwork
    public String customIdGenerator(Artwork artwork)
    {
        String prefix = "";

        int uniqueNumber = getUniqueNumber(); // Retrieve a unique number based on the current timestamp
        String id = "art" + String.format("%04d", uniqueNumber);
        return id;
    }

    private int getUniqueNumber() {
        long timestamp = System.currentTimeMillis();
        return (int) (timestamp % 10000);
    }
}