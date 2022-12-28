package com.example.demo.repository

import com.example.demo.model.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository : JpaRepository<Role, Long> {
  fun findRoleByName(name: String) : Role?
}