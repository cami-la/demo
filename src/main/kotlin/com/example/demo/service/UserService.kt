package com.example.demo.service

import com.example.demo.dto.request.SignUpRequestDto
import com.example.demo.model.User

interface UserService {
  fun saveUser(signUpRequestDto: SignUpRequestDto): User
}