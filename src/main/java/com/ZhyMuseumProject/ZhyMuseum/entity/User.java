package com.ZhyMuseumProject.ZhyMuseum.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.HibernateException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "tbl_User")
public class User implements UserDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotEmpty(message = "Please Provide a name")
    private String username;

//    @Pattern(regexp = "[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
//            + "[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
//            + "(?:[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?\\.)+[A-Za-z0-9]"
//            + "(?:[A-Za-z0-9-]*[A-Za-z0-9])?",
//            message = "Provide a valid email address.")
    private String email;

    
    // -----------------------------------------
    // should uppercase and letter and at least 6 characters
//    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,}",
//            message = "Password must have at least 6 characters and contain at least one number, one lowercase and one uppercase letter.")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Artwork> artworks;


    public User() {
    }

    //constructors, getters, setters, equals, hashCode and toString;

    public User(String username, String email, String password,Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role =role;
    }

    public int getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(username, user.username) && Objects.equals(email, user.email) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    // Generate custom ID for each of Artist and Artlover
    public String customIdGenerator(User user)
    {
        String prefix = "";

        if (user.getRole().equals("ARTIST"))
            prefix = "artist";
        else if (user.getRole().equals("ARTLOVER"))
            prefix = "artlover";
        else
            throw new HibernateException("Unsupported entity type: " + user.getClass().getSimpleName());

        int uniqueNumber = getUniqueNumber(); // Retrieve a unique number based on the current timestamp
        String id = prefix + String.format("%04d", uniqueNumber);
        return id;
    }

    private int getUniqueNumber() {
        long timestamp = System.currentTimeMillis();
        return (int) (timestamp % 10000);
    }


    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }



}
