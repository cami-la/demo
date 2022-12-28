package com.example.demo.configuration

import com.example.demo.service.impl.UserServiceImpl
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationManager(
  private val passwordEncoder: PasswordEncoder,
  private val userDetailsService: UserServiceImpl
) {
  fun authenticate(authentication: Authentication): Authentication {
    val userDetails: UserDetails = userDetailsService.loadUserByUsername(authentication.name)
    if(!passwordEncoder.matches(authentication.credentials.toString(), userDetails.password))
      throw RuntimeException("Wrong password")
    return UsernamePasswordAuthenticationToken(userDetails.username, userDetails.password, userDetails.authorities)
  }
}