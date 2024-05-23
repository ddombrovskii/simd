package ru.ssau.simd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ssau.simd.exception.NoEntityException;
import ru.ssau.simd.pojo.UserPojo;
import ru.ssau.simd.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserPojo> createUser(@RequestBody UserPojo pojo) {
        return new ResponseEntity<>(userService.saveUser(pojo), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserPojo>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserPojo> getUserById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
        } catch (NoEntityException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserPojo> updateUser(@PathVariable("id") Long id, @RequestBody UserPojo pojo) {
        try {
            return new ResponseEntity<>(userService.updateUser(id, pojo), HttpStatus.OK);
        } catch (NoEntityException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoEntityException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
