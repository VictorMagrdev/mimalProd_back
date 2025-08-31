Feature: Autenticación y sesión (JWT)
  Como usuario del sistema
  Quiero iniciar y cerrar sesión con JWT
  Para acceder a recursos protegidos

  Background:
    Given existe un usuario "admin" con contraseña "AdminPass123" y rol "Admin"
    And el usuario "admin" está activado

  Scenario: Login exitoso y obtención de JWT
    When "admin" inicia sesión con contraseña "AdminPass123"
    Then recibe un token JWT válido

  Scenario: Login fallido con contraseña incorrecta
    When "admin" inicia sesión con contraseña "wrong"
    Then recibe un error de autenticación "Credenciales incorrectas"

  Scenario: Usuario desactivado no puede iniciar sesión
    Given existe un usuario "pepe" con contraseña "secret" y rol "Engineer"
    And el usuario "pepe" está desactivado
    When "pepe" intenta iniciar sesión con contraseña "secret"
    Then recibe un error de autenticación "Usuario inactivo or deshabilitado"

  Scenario: Logout invalida el token
    Given "admin" tiene un token JWT válido
    When "admin" realiza logout con su token
    Then ese token ya no permite acceder a recursos protegidos
