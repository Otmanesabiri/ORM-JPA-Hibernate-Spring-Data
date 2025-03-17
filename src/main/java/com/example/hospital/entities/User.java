package com.example.hospital.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "users")
@Data @NoArgsConstructor @AllArgsConstructor
public class User {
    @Id
    private String userId;
    
    @Column(unique = true, length = 20)
    private String username;
    
    private String password;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", 
               joinColumns = @JoinColumn(name = "user_id"), 
               inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles = new ArrayList<>();
}
