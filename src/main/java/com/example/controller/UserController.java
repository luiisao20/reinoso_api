package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.User;
import com.example.service.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/users")
public class UserController {
  @Autowired
  private UserService userService;

  @GetMapping()
  public List<User> getAll() {
    return userService.getUsers();
  }

  @GetMapping("/{uuid}")
  public Optional<User> getUserById(@PathVariable String uuid) {
    return userService.getUserById(uuid);
  }

  @PostMapping("/register")
  public User saveUser(@RequestBody User user) {
    return userService.registerUser(user);
  }

  @PutMapping("/update/{uuid}")
  public User updateUser(@PathVariable String uuid, @RequestBody User user) {
    return userService.updateUser(uuid, user);
  }

  @DeleteMapping("/delete/{uuid}")
  public void delete(@PathVariable String uuid) {
    userService.deleteUser(uuid);
  }

}
