package com.example.hospital.repositories;

import com.example.hospital.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
