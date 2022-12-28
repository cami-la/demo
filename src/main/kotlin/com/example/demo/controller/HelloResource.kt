package com.example.demo.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloResource {

  @GetMapping("/")
  fun hello() : String = "Hello Spring with Kotlin"
}