package com.example.demo.controller

import com.example.demo.dto.request.SignUpRequestDto
import com.example.demo.dto.response.RestResponseDto
import com.example.demo.service.UserService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI

@RestController
class UserController(
  private val userService: UserService
) {

  @PostMapping("/sign-up")
  fun signUp(@RequestBody @Valid signUpRequestDto: SignUpRequestDto): ResponseEntity<RestResponseDto> {
    val saveUser = this.userService.saveUser(signUpRequestDto)
    val location: URI = ServletUriComponentsBuilder.fromCurrentRequest()
      .path("/{id}")
      .buildAndExpand(saveUser)
      .toUri()
    return ResponseEntity.created(location).body(RestResponseDto(true, "User ${saveUser.email} saved"))
  }

}