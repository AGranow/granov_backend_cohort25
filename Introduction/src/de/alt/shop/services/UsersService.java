package de.alt.shop.services;

import de.alt.shop.models.User;

import java.util.List;

public interface UsersService {

    User addUser(String email, String password);

    List<User> getAllUsers();
}
