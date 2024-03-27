package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.service.UserSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.model.User;

import java.util.List;

@RestController
@RequestMapping("/user")
@Tag(name = "User", description = "User endpoints")
public class UserController {

    @Autowired
    private UserSvc userSvc;

    @Operation(summary = "Get all users", description = "Get all users from database")
    @GetMapping("/all")
    public ResponseEntity<List<User>> findAllUsers() {
        return new ResponseEntity<>(userSvc.findAllUsers(), HttpStatus.OK);
    }

    @Operation(summary = "Get specific user", description = "Get specific user by using ID from database")
    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable("id") Long id) {
        return (userSvc.findUserById(id) != null) ? new ResponseEntity<>(userSvc.findUserById(id), HttpStatus.OK) : new ResponseEntity<>(userSvc.findUserById(id), HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Save user", description = "Save user to database")
    @PostMapping("/save")
    public ResponseEntity<Void> saveUser(@RequestBody User user) {
        userSvc.saveUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Update user", description = "Update user using ID from database")
    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        user.setUserId(id);
        userSvc.updateUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Delete users", description = "Delete user using ID from database")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userSvc.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
