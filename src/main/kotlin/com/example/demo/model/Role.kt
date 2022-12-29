package com.example.demo.model

import jakarta.persistence.*

@Entity
@Table(name = "roles_tbl")
data class Role(
  val name: String,
  @ManyToMany(fetch = FetchType.EAGER) @JoinTable(name = "roles_privileges") val privileges: MutableCollection<Privilege>,
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null
)
