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
    public User findUserById(@PathVariable("id") Long id) {
        return userRepo.findUserById(id);
    }

    @Override
    @CachePut(value = "saveUserCache", key = "#user.userId")
    public void saveUser(@RequestBody User user) {
        userRepo.saveUser(user);
    }

    @Override
    @Caching(evict = @CacheEvict(value = "userCache", key = "'user_' + #user.userId", beforeInvocation = true), put = @CachePut(value = "updateUserCache", key = "'user_' + #user.userId"))
    public void updateUser(@RequestBody User user) {
        userRepo.updateUser(user);
    }

    @Override
    @CacheEvict(value = "deleteUserCache", key = "'user_'" + "#id")
    public void deleteUser(Long id) {
        userRepo.deleteUser(id);
    }
}
