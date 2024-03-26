package org.example.repo;

import org.apache.ibatis.annotations.Mapper;
import org.example.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserRepo {
    List<User> findAllUsers();
    User findUserById(Long id);
    void saveUser(User user);
    void updateUser(User user);
    void deleteUser(Long id);
}
