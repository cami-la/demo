package com.example.demo.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class SecurityConfig(
  private val authenticationEntryPoint: MyBasicAuthenticationEntryPoint
) {

  @Bean
  fun filterChain(http: HttpSecurity): SecurityFilterChain {
    http
      .cors()
      .and()
      .csrf().disable()
      .authorizeHttpRequests()
      //.requestMatchers("/").hasRole("USER")
      .requestMatchers("/").hasAuthority("READ_PRIVILEGE")
      .requestMatchers("/sign-up").permitAll()
      .anyRequest().authenticated()
      .and()
      .httpBasic()
      .authenticationEntryPoint(this.authenticationEntryPoint)
      .and()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    return http.build()
  }

  @Bean
  fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}