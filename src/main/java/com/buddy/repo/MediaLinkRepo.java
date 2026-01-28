/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.buddy.repo;

/**
 *
 * @author serra
 */

import com.buddy.model.MediaLink;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MediaLinkRepo extends JpaRepository<MediaLink, Long> {
    List<MediaLink> findByOwnerId(Long ownerId);
}

