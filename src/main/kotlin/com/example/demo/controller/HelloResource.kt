package com.example.demo.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloResource {

  @GetMapping("/")
  fun hello() : String = "<h1>Hello Spring with Kotlin</h1>"
}