package com.example.hospital.service;

import com.example.hospital.entities.Role;
import com.example.hospital.entities.User;

public interface UserService {
    User addNewUser(User user);
    Role addNewRole(Role role);
    User findUserByUsername(String username);
    Role findRoleByName(String name);
    void addRoleToUser(String username, String roleName);
}
