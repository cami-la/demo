package com.example.demo.exception

data class BusinessException(
  override val message: String?,
) : RuntimeException(message)