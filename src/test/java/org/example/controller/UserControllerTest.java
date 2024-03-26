package org.example.controller;

import org.example.model.User;
import org.example.service.UserSvc;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertSame;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserSvc userSvc;

    @InjectMocks
    private UserController userController;

    @Test
    void testFindAllUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1L, "User A", "Email A", "Job A", 1L));
        userList.add(new User(2L, "User B", "Email B", "Job B", 2L));
        when(userSvc.findAllUsers()).thenReturn(userList);

        ResponseEntity<List<User>> response = userController.findAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userList, response.getBody());
    }

    @Test
    void testFindUserById() {
        Long userId = 1L;
        User user = new User(1L, "User A", "Email A", "Job A", 1L);
        when(userSvc.findUserById(userId)).thenReturn(user);

        ResponseEntity<User> response = userController.findUserById(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void testFindUserByIdNotFound() {
        Long userId = 1L;
        when(userSvc.findUserById(userId)).thenReturn(null);

        ResponseEntity<User> response = userController.findUserById(userId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testSaveUser() {
        User user = new User(1L, "User A", "Email A", "Job A", 1L);
        doNothing().when(userSvc).saveUser(user);

        ResponseEntity<Void> response = userController.saveUser(user);

        assertSame(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void testUpdateUser() {
        Long userId = 1L;
        User user = new User();
        doNothing().when(userSvc).updateUser(user);

        ResponseEntity<Void> response = userController.updateUser(userId, user);

        assertSame(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testDeleteUser() {
        Long userId = 1L;
        doNothing().when(userSvc).deleteUser(userId);

        ResponseEntity<Void> response = userController.deleteUser(userId);

        assertSame(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
