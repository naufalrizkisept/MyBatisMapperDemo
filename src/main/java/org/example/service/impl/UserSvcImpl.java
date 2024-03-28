package org.example.service.impl;

import jakarta.transaction.Transactional;
import org.example.model.User;
import org.example.repo.UserRepo;
import org.example.service.UserSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@Transactional
public class UserSvcImpl implements UserSvc {

    @Autowired
    private UserRepo userRepo;

    @Override
    @Cacheable(value = "listUserCache")
    public List<User> findAllUsers() {
        return userRepo.findAllUsers();
    }

    @Override
    @Cacheable(value = "userCache", key = "'user_' + #id")
    public User findUserById(Long id) {
        return userRepo.findUserById(id);
    }

    @Override
    @CachePut(value = "saveUserCache")
    public void saveUser(User user) {
        userRepo.saveUser(user);
    }

    @Override
    @CachePut(value = "updateUserCache", key = "'user_' + #user.userId")
    public void updateUser(User user) {
        userRepo.updateUser(user);
    }

    @Override
    @CacheEvict(value = "deleteUserCache", key = "'user_'" + "#id")
    public void deleteUser(Long id) {
        userRepo.deleteUser(id);
    }
}
