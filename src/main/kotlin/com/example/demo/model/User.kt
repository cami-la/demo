package com.example.demo.model

import jakarta.persistence.*

@Entity
@Table(name = "users_tbl")
data class User(
  var firstName: String,
  var lastName: String,
  var email: String,
  var password: String,
  var isEnable: Boolean = true,
  @ManyToMany @JoinTable(name = "users_roles") var roles: MutableCollection<Role?>,
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null
)
