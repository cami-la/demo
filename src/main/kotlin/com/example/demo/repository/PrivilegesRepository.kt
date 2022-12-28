package com.example.demo.repository

import com.example.demo.model.Privilege
import org.springframework.data.jpa.repository.JpaRepository

interface PrivilegesRepository: JpaRepository<Privilege, Long>{
  fun findPrivilegeByName(name: String?) : Privilege?
}