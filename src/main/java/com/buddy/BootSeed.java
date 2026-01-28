/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.buddy;

/**
 *
 * @author g.serranoPerez
 */


import com.buddy.model.User;
import com.buddy.repo.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BootSeed {
  @Bean
  CommandLineRunner seed(UserRepo users, PasswordEncoder enc) {
    return args -> {
      if (users.findByUsername("demo").isEmpty()) {
        User u = new User();
        u.setUsername("demo");
        u.setPasswordHash(enc.encode("demo123")); // must be BCrypt
        users.save(u);
        System.out.println("Seeded user: demo / demo123");
      }
    };
  }
}
