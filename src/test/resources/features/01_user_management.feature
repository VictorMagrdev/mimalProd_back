Feature: Gestión de usuarios
  Como administrador
  Quiero crear, editar y desactivar usuarios
  Para controlar quién puede usar la aplicación

  Background:
    Given existe un usuario "admin" con rol "Admin" y está autenticado

  Scenario: Crear un nuevo usuario por admin
    When el admin crea un usuario con username "juan", email "juan@acme", rol "Engineer"
    Then el usuario "juan" existe con rol "Engineer" y estado "activo"

  Scenario: Intento de creación de usuario por usuario no-admin falla
    Given existe un usuario "bob" con rol "Engineer" y está autenticado
    When "bob" intenta crear un usuario con username "luis"
    Then la operación es denegada con error "Permiso denegado"

  Scenario: Editar datos de usuario (email y nombre)
    Given existe un usuario "maria" con rol "Engineer"
    When el admin actualiza el email de "maria" a "maria@empresa"
    Then el usuario "maria" tiene email "maria@empresa"

  Scenario: Desactivar usuario por admin
    Given existe un usuario "carlos" activo
    When el admin desactiva al usuario "carlos"
    Then el usuario "carlos" está desactivado
    And "carlos" no puede iniciar sesión

  Scenario: Re-activar usuario por admin
    Given existe un usuario "carlos" desactivado
    When el admin reactiva al usuario "carlos"
    Then el usuario "carlos" está activo
