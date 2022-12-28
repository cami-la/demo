package com.example.demo.model

import jakarta.persistence.*

@Entity
@Table(name = "privileges_tbl")
data class Privilege(
  val name: String?,
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null
)
