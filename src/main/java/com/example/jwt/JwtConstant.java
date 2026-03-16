package com.example.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtConstant {
  public static final String JWT_HEADER = "Authorization";

  public static String SECRET_KEY_STATIC;

  @Value("${jwt.secret}")
  private String secretKey;

  @jakarta.annotation.PostConstruct
  public void init() {
    SECRET_KEY_STATIC = secretKey;
  }

  public static String getSecretKey() {
    return SECRET_KEY_STATIC;
  }
}
