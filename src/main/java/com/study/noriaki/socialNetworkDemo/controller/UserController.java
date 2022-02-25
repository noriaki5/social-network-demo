package com.study.noriaki.socialNetworkDemo.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.study.noriaki.socialNetworkDemo.exception.UserNotFoundException;
import com.study.noriaki.socialNetworkDemo.model.User;
import com.study.noriaki.socialNetworkDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable long id) {
        Optional<User> user = userService.findById(id);

        if (!user.isPresent()) {
            throw new UserNotFoundException("id=" + id);
        }

        return user.get();
    }

    @GetMapping("/users/name/{name}")
    public List<User> findByName(@PathVariable String name) {
        List<User> users = userService.findByName(name);

        if (users.isEmpty()) {
            throw new UserNotFoundException("name=" + name);
        }

        return users;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        User savedUser = userService.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable long id) {
        Optional<User> userOptional = userService.findById(id);

        if (!userOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        user.setId(id);
        userService.save(user);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/friends/{id}")
    public Set<User> retrieveFriends(@PathVariable long id) {
        Optional<User> user = userService.findById(id);

        if (!user.isPresent()) {
            throw new UserNotFoundException("id=" + id);
        }

        return user.get().getFriends();
    }

    @PostMapping("/user/friends")
    public ResponseEntity<Object> addFriend(@RequestBody ObjectNode jsonNodes) {
        long id = jsonNodes.get("id").asLong();
        long friendId = jsonNodes.get("friendId").asLong();

        if (id == friendId) {
            return ResponseEntity.badRequest().build();
        }

        User user = userService.getById(id);
        User friend = userService.getById(friendId);
        user.getFriends().add(friend);
        userService.save(user);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/user/friends")
    public ResponseEntity<Object> updateUser(@RequestBody ObjectNode jsonNodes) {
        long id = jsonNodes.get("id").asLong();
        long friendId = jsonNodes.get("friendId").asLong();

        User user = userService.getById(id);
        User friend = userService.getById(friendId);
        user.getFriends().remove(friend);
        userService.save(user);

        return ResponseEntity.noContent().build();
    }
}