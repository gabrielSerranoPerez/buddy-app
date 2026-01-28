/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.buddy.model;

/**
 *
 * @author serra
 */

import jakarta.persistence.*;

@Entity
public class MediaLink {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;   // e.g., "BBC News", "YouTube – Relaxing Music"
  private String url;     // e.g., https://www.bbc.com/news

  @ManyToOne @JoinColumn(name="owner_id")
  private User owner;

  // getters/setters
}
