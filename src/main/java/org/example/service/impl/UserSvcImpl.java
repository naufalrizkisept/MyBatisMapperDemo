package org.example.service.impl;

import jakarta.transaction.Transactional;
import org.example.model.User;
import org.example.repo.UserRepo;
import org.example.service.UserSvc;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserSvcImpl implements UserSvc {

    private UserRepo userRepo;

    @Override
    public List<User> findAllUsers() {
        return userRepo.findAllUsers();
    }

    @Override
    public User findUserById(Long id) {
        return userRepo.findUserById(id);
    }

    @Override
    public void saveUser(User user) {
        userRepo.saveUser(user);
    }

    @Override
    public void updateUser(User user) {
        userRepo.updateUser(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteUser(id);
    }
}
