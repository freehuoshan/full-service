package com.example.usersapi.controllers;


import com.example.usersapi.models.User;
import com.example.usersapi.repositories.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.usersapi.models.User;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.ArrayList;

@RestController
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{userId}")
    public User findUserById(@PathVariable Long userId) throws NotFoundException {
        User foundUser = userRepository.findOne(userId);
        if (foundUser == null) {
            throw new NotFoundException("User with ID of " + userId + " was not found!");
        }

        return foundUser;
    }
//
//    @GetMapping("/{userId}/requests")
//    public ArrayList<String> findAllRequestsForUserId(@PathVariable long userId, @PathVariable String requestId) throws NotFoundException {
//        User foundUser = userRepository.findOne(userId);
//        if (foundUser == null) {
//            throw new NotFoundException("User with ID of " + userId + " was not found!");
//        }
//        return foundUser.getRequestIds();
//    }

    @DeleteMapping("/{userId}")
    public HttpStatus deleteUserById(@PathVariable Long userId) throws EmptyResultDataAccessException {
        userRepository.delete(userId);
        return HttpStatus.OK;
    }

    @PostMapping("/")
    public User createNewUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

//    @PatchMapping("/{userId}")
//    public User updateUserById(@PathVariable Long userId, @RequestBody User userRequest) throws NotFoundException {
//        User userFromDb = userRepository.findOne(userId);
//
//        if (userFromDb == null) {
//            throw new NotFoundException("User with ID of " + userId + " was not found!");
//        }
//
//        userFromDb.setUserName(userRequest.getUserName());
//        userFromDb.setFirstName(userRequest.getFirstName());
//        userFromDb.setLastName(userRequest.getLastName());
//
//        return userRepository.save(userFromDb);
//    }

    @ExceptionHandler
    void handleUserNotFound(
            NotFoundException exception,
            HttpServletResponse response) throws IOException {

        response.sendError(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    @ExceptionHandler
    void handleDeleteNotFoundException(
            EmptyResultDataAccessException exception,
            HttpServletResponse response) throws IOException {

        response.sendError(HttpStatus.NOT_FOUND.value());
    }
//
//    @DeleteMapping("/{userId}/requests/{requestId}")
//    public HttpStatus deleteRequestIdForUser(@PathVariable Long userId, @PathVariable String requestId) throws NotFoundException {
//        User foundUser = userRepository.findOne(userId);
//        if (foundUser == null) {
//            throw new NotFoundException("User with ID of " + userId + " was not found!");
//
//        }
//        ArrayList<String> newRequests = foundUser.getRequestIds();
//        newRequests.remove(requestId);
//        foundUser.setRequestIds(newRequests);
//        userRepository.save(foundUser);
//        return HttpStatus.OK;
//
//    }
//
//    @PatchMapping("/{userId}/requests/add/{requestId}")
//    public HttpStatus addRequestIdToUser(@PathVariable long userId, @PathVariable String requestId) throws NotFoundException {
//        User foundUser = userRepository.findOne(userId);
//        if (foundUser == null) {
//            throw new NotFoundException("User with ID of " + userId + " was not found!");
//        }
//        ArrayList<String> newRequests = foundUser.getRequestIds();
//        newRequests.add(requestId);
//        foundUser.setRequestIds(newRequests);
//        userRepository.save(foundUser);
//        return HttpStatus.OK;
//    }

}