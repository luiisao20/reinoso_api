package com.example.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.User;
import com.example.jwt.JwtProvider;
import com.example.reponse.AuthResponse;
import com.example.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/auth")
public class AuthController {
  @Autowired
  private UserService userService;

  @GetMapping("validate")
  public ResponseEntity<Map<String, Object>> validateToken() {
    Map<String, Object> response = new HashMap<>();
    response.put("message", "Token is valid");

    return ResponseEntity.ok(response);
  }

  @PostMapping("/login")
  public ResponseEntity<AuthResponse> login(@RequestBody User user) {
    Authentication authentication = userService.loginAction(user.getEmail(), user.getPassword());
    Optional<User> currentUser = userService.getUserByEmail(user.getEmail());

    SecurityContextHolder.getContext().setAuthentication(authentication);

    String jwt = JwtProvider.generateToken(authentication);

    AuthResponse response = new AuthResponse();
    response.setEmail(currentUser.get().getEmail());
    response.setRole(currentUser.get().getRole().name());
    response.setJwt(jwt);
    response.setMessage("Login exitoso");

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

}
