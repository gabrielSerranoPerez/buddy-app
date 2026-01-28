/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.buddy.web;

/**
 *
 * @author g.serranoPerez
 */

import com.buddy.model.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.buddy.repo.UserRepo;

@Controller @Validated
public class AuthController {
  record SignUpForm(@NotBlank String username, @NotBlank String password) {}

  private final UserRepo userRepo;
  private final PasswordEncoder encoder;

  public AuthController(UserRepo userRepo, PasswordEncoder encoder) {
    this.userRepo = userRepo; 
    this.encoder = encoder;
  }

  @GetMapping("/login") String login() { return "login"; }

  @GetMapping("/signup")
  String signup(Model model) {
    model.addAttribute("form", new SignUpForm("", ""));
    return "signup";
  }

  @PostMapping("/signup")
  String doSignup(@ModelAttribute("form") SignUpForm f, Model model) {
    if (userRepo.findByUsername(f.username()).isPresent()) {
      model.addAttribute("error", "Username already exists");
      return "signup";
    }
    User u = new User();
    u.setUsername(f.username());
    u.setPasswordHash(encoder.encode(f.password()));
    userRepo.save(u);
    return "redirect:/login?created";
  }
}
