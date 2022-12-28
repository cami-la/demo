package com.example.demo.dto.request

import jakarta.validation.constraints.NotBlank

data class SignInRequestDto(
  @field:NotBlank(message = "This field cannot be empty") val email: String,
  @field:NotBlank(message = "This field cannot be empty") val password: String
)
