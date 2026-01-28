/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.buddy.web;

/**
 *
 * @author g.serranoPerez
 */

import com.buddy.model.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.buddy.repo.UserRepo;
import com.buddy.repo.ContactRepo;
import com.buddy.repo.MediaLinkRepo;

@Controller
public class HomeController {
  private final UserRepo userRepo;
  private final ContactRepo contactRepo;
  private final MediaLinkRepo mediaRepo;

  public HomeController(UserRepo userRepo, ContactRepo contactRepo, MediaLinkRepo mediaRepo) {
    this.userRepo = userRepo; this.contactRepo = contactRepo; this.mediaRepo = mediaRepo;
  }

  @GetMapping("/")
  String home(Authentication auth, Model model) {
    User current = userRepo.findByUsername(auth.getName()).orElseThrow();
    model.addAttribute("contacts", contactRepo.findByOwnerId(current.getId()));
    model.addAttribute("medias", mediaRepo.findByOwnerId(current.getId()));
    return "home";
  }

  // Simple CRUD endpoints you can add:
  // POST /contacts, DELETE /contacts/{id}
  // POST /media, DELETE /media/{id}
}
