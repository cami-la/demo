package com.example.demo.dto.request

import com.example.demo.model.User
import jakarta.validation.constraints.NotBlank

data class SignUpRequestDto(
  @field:NotBlank(message = "This field cannot be empty") val firstName: String,
  @field:NotBlank(message = "This field cannot be empty") val lasName: String,
  @field:NotBlank(message = "This field cannot be empty") val password: String,
  @field:NotBlank(message = "This field cannot be empty") val email: String
) {
  fun toEntity(): User = User(
    firstName = this.firstName,
    lastName = this.lasName,
    email = this.email,
    password = password
  )
}