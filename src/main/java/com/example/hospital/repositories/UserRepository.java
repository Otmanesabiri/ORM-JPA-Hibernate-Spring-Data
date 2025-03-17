package com.example.hospital.repositories;

import com.example.hospital.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
}
