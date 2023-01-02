package com.example.demo.configuration

import com.example.demo.model.Privilege
import com.example.demo.model.Role
import com.example.demo.model.User
import com.example.demo.repository.PrivilegesRepository
import com.example.demo.repository.RoleRepository
import com.example.demo.repository.UserRepository
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional


@Component
class SetupDataLoader(
  private var alreadySetup: Boolean = false,
  private val userRepository: UserRepository,
  private val roleRepository: RoleRepository,
  private val privilegeRepository: PrivilegesRepository,
  private val passwordEncoder: PasswordEncoder,
) : ApplicationListener<ContextRefreshedEvent> {

  @Transactional
  override fun onApplicationEvent(event: ContextRefreshedEvent) {
    if (alreadySetup) return
    val readPrivilege: Privilege = this.createPrivilegeIfNotFound("READ_PRIVILEGE")
    val writePrivilege: Privilege = this.createPrivilegeIfNotFound("WRITE_PRIVILEGE")
    createRoleIfNotFound("ROLE_USER", mutableSetOf(readPrivilege))
    createRoleIfNotFound("ROLE_ADMIN", mutableSetOf(readPrivilege, writePrivilege))

    val findRoleUserByName = this.roleRepository.findRoleByName("ROLE_USER")
    val findRoleAdminByName = this.roleRepository.findRoleByName("ROLE_ADMIN")

    val user: User = User(
      firstName = "Anna",
      lastName = "Silva",
      password = passwordEncoder.encode("123456"),
      email = "anna@email.com",
      roles = mutableSetOf(findRoleUserByName)
    )

    val admin: User = User(
      firstName = "Cami",
      lastName = "Cavalcante",
      password = passwordEncoder.encode("123456"),
      email = "cami@email.com",
      roles = mutableSetOf(findRoleAdminByName)
    )

    this.userRepository.save(user)
    this.userRepository.save(admin)
    println("Entity Data Setup: $user")
    println("Entity Data Setup: $admin")

    this.alreadySetup = true
  }

  @Transactional
  fun createPrivilegeIfNotFound(name: String): Privilege {
    var privilege: Privilege? = this.privilegeRepository.findPrivilegeByName(name)
    if (privilege == null) {
      privilege = Privilege(name)
      this.privilegeRepository.save(privilege)
    }
    return privilege
  }

  @Transactional
  fun createRoleIfNotFound(name: String, privileges: MutableCollection<Privilege>): Unit {
    var role: Role? = this.roleRepository.findRoleByName(name)
    if (role == null) {
      role = Role(name, privileges)
      this.roleRepository.save(role)
    }
  }
}