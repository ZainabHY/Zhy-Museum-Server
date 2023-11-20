package com.ZhyMuseumProject.ZhyMuseum.Repository;

import com.ZhyMuseumProject.ZhyMuseum.entity.Role;
import com.ZhyMuseumProject.ZhyMuseum.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String name);
    User findByRole(Role roles);
}
