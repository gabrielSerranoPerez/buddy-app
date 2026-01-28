/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.buddy.model;

/**
 *
 * @author g.serranoPerez
 */

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Contact {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank private String displayName;
  @NotBlank private String phoneNumber; // E.164 recommended: +15551234567
  private String videoLink; // e.g., Zoom/Meet/FaceTime/WhatsApp deep link

  @ManyToOne @JoinColumn(name="owner_id")
  private User owner;

  // getters/setters
}
