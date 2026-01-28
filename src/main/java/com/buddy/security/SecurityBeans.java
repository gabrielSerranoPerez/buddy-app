package com.buddy.security;

/**
 *
 * @author g.serranoPerez
 */

import com.buddy.repo.UserRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityBeans {
  @Bean PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

  @Bean
  UserDetailsService userDetailsService(UserRepo repo) {
    return username -> repo.findByUsername(username)
      .map(u -> org.springframework.security.core.userdetails.User
          .withUsername(u.getUsername())
          .password(u.getPasswordHash())   // the BCrypt hash you saved
          .roles("USER")
          .build())
      .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
  }
}
