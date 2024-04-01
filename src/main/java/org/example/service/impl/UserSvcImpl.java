package org.example.service.impl;

import jakarta.transaction.Transactional;
import org.example.model.User;
import org.example.repo.UserRepo;
import org.example.service.UserSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class UserSvcImpl implements UserSvc {

    @Autowired
    private UserRepo userRepo;

    @Override
    @Cacheable(value = "listUserCache", cacheManager = "cacheManager")
    public List<User> findAllUsers() {
        return userRepo.findAllUsers();
    }

    @Override
    @Cacheable(value = "userCache", cacheManager = "cacheManager")
    public User findUserById(Long id) {
        return userRepo.findUserById(id);
    }

    @Override
    @CachePut(value = "saveUserCache", cacheManager = "cacheManager")
    public void saveUser(User user) {
        userRepo.saveUser(user);
    }

    @Override
    @CachePut(value = "updateUserCache", cacheManager = "cacheManager")
    public void updateUser(User user) {
        userRepo.updateUser(user);
    }

    @Override
    @CacheEvict(value = "deleteUserCache", beforeInvocation = true, cacheManager = "cacheManager")
    public void deleteUser(Long id) {
        userRepo.deleteUser(id);
    }
}
