package com.example.demo.dto.response

import java.util.*

data class RestResponseDto(
  var success: Boolean,
  var message: String,
  var currentDate: Date = Date(),
)
