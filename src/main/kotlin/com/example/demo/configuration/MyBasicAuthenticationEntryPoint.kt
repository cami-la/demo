package com.example.demo.configuration

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint
import org.springframework.stereotype.Component
import java.io.PrintWriter

@Component
class MyBasicAuthenticationEntryPoint : BasicAuthenticationEntryPoint() {
  override fun commence(
    request: HttpServletRequest?,
    response: HttpServletResponse?,
    authException: AuthenticationException?
  ) {
    response?.addHeader("WWW-Authenticate", "Basic realm=\"\"$realmName\"")
    response?.status = HttpServletResponse.SC_UNAUTHORIZED
    val writer: PrintWriter? = response?.writer
    writer?.println("HTTP Status 401 - " + authException?.message.toString())
    super.commence(request, response, authException)
  }

  override fun afterPropertiesSet() {
    setRealmName("cami")
    super.afterPropertiesSet()
  }
}