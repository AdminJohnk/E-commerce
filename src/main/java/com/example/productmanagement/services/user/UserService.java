package com.example.productmanagement.services.user;

import com.example.productmanagement.dtos.user.UserDTO;
import com.example.productmanagement.entities.User;

import java.util.List;
import java.security.Principal;

public interface UserService {
    User getUser(String id);
    User getUserByEmail(String email);
    List<User> getAllUser();
    User create(UserDTO dto, Principal principal);
    User update(String id, UserDTO dto, Principal principal);
    User delete(String id);
}