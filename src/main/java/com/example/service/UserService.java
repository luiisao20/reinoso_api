package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.roles.Role;

@Service
public class UserService implements UserDetailsService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public List<User> getUsers() {
    return userRepository.findAll();
  }

  public Optional<User> getUserById(String uuid) {
    return userRepository.findById(uuid);
  }

  public Optional<User> getUserByEmail(String email) {
    return userRepository.findUserByEmail(email);
  }

  public User updateUser(String uuid, User user) {
    User oldUser = getUserByEmail(user.getEmail())
        .orElseThrow(() -> new RuntimeException("User not found"));

    oldUser.setEmail(user.getEmail());
    oldUser.setName(user.getName());
    oldUser.setRole(user.getRole());

    if (user.getUuid() != null && !user.getPassword().trim().isEmpty()) {
      oldUser.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    return userRepository.save(oldUser);
  }

  public void deleteUser(String uuid) {
    userRepository.deleteById(uuid);
  }

  public User registerUser(User user) {
    String passwordEncoded = passwordEncoder.encode(user.getPassword());
    user.setPassword(passwordEncoded);
    user.setRole(Role.ROLE_USER);
    return userRepository.save(user);
  }

  @Override
  public UserDetails loadUserByUsername(String username) {
    User user = userRepository.findUserByEmail(username)
        .orElseThrow(() -> new RuntimeException("User not found"));

    return org.springframework.security.core.userdetails.User
        .builder()
        .username(user.getEmail())
        .password(user.getPassword())
        .authorities(user.getRole().name())
        .build();
  }

  public Authentication loginAction(String email, String password) {
    UserDetails userDetails = loadUserByUsername(email);

    if (passwordEncoder.matches(password, userDetails.getPassword())) {
      return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    throw new RuntimeException("Error de autenticacion");
  }
}
