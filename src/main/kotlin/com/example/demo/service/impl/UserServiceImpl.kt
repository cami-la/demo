package com.example.demo.service.impl

import com.example.demo.dto.request.SignUpRequestDto
import com.example.demo.exception.BusinessException
import com.example.demo.model.Role
import com.example.demo.model.User
import com.example.demo.repository.RoleRepository
import com.example.demo.repository.UserRepository
import com.example.demo.service.UserService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
  private val userRepository: UserRepository,
  private val passwordEncoder: BCryptPasswordEncoder,
  private val roleRepository: RoleRepository
) : UserService, UserDetailsService {

  override fun loadUserByUsername(email: String?): UserDetails {
    val user: User = email?.let {
      this.userRepository.findUserByEmail(it)
    } ?: throw UsernameNotFoundException("User with email $email not found")
    return UserDetailsImpl(user)
  }

  @Transactional
  override fun saveUser(signUpRequestDto: SignUpRequestDto): User {
    val user: User? = this.userRepository.findUserByEmail(signUpRequestDto.email)
    if (user != null) {
      throw BusinessException("User with email ${user.email} already exists")
    }
    val encodedPassword : String = passwordEncoder.encode(signUpRequestDto.password)
    val entityToSave : User = signUpRequestDto.toEntity().apply {
      password = encodedPassword
    }
    val roleUser: Role? = this.roleRepository.findRoleByName("ROLE_USER")
    user?.roles = mutableSetOf(roleUser)
    return this.userRepository.save(entityToSave)
  }
}