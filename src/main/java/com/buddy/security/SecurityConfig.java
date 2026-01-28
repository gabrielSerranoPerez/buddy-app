
package com.buddy.security;

/**
 *
 * @author g.serranoPerez
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .authorizeHttpRequests(auth -> auth
        .requestMatchers("/", "/index.html", "/signup", "/login",
                         "/css/**", "/js/**", "/images/**", "/h2-console/**").permitAll()
        .anyRequest().authenticated()
      )
      .formLogin(form -> form
        .loginPage("/login").permitAll()
        .defaultSuccessUrl("/", true)   // after login, go to /
      )
      .logout(l -> l.logoutSuccessUrl("/login?logout").permitAll());

    http.csrf(cs -> cs.ignoringRequestMatchers("/h2-console/**")); // or cs.disable() during dev
    http.headers(h -> h.frameOptions(f -> f.disable()));

    return http.build();
  }
}
