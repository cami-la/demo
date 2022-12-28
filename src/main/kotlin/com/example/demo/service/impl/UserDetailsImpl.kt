package com.example.demo.service.impl

import com.example.demo.model.Privilege
import com.example.demo.model.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetailsImpl(
  private val user: User,
) : UserDetails {

  override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
    val authorities: MutableCollection<SimpleGrantedAuthority> = mutableSetOf()
    this.getPrivileges().forEach { p -> authorities.add(SimpleGrantedAuthority(p)) }
    return authorities
  }

  private fun getPrivileges(): MutableCollection<String> {
    val collection: MutableSet<Privilege> = mutableSetOf()
    val privileges: MutableSet<String> = mutableSetOf()
    this.user.roles.forEach {
      if (it != null) {
        privileges.add(it.name)
      }
      if (it != null) {
        collection.addAll(it.privileges)
      }
    }
    collection.forEach { it.name?.let { it1 -> privileges.add(it1) } }
    return privileges
  }

  override fun getPassword(): String = this.user.password

  override fun getUsername(): String = this.user.email

  override fun isAccountNonExpired(): Boolean = true

  override fun isAccountNonLocked(): Boolean = true

  override fun isCredentialsNonExpired(): Boolean = true

  override fun isEnabled(): Boolean = this.user.isEnable
}