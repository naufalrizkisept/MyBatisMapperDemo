package org.example.service;

import org.example.model.User;

import java.util.List;

public interface UserSvc {
    List<User> findAllUsers();
    User findUserById(Long id);
    void saveUser(User user);
    void updateUser(User user);
    void deleteUser(Long id);
}
