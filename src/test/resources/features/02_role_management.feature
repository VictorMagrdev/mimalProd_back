Feature: Gestión de roles
  Como administrador
  Quiero crear, editar y eliminar roles
  Para definir responsabilidades y asignar permisos por política

  Background:
    Given existe un usuario "admin" con rol "Admin" y está autenticado

  Scenario: Crear un nuevo rol
    When el admin crea el rol "Accounting"
    Then el rol "Accounting" existe en el sistema

  Scenario: Intento de crear rol por no-admin es denegado
    Given existe un usuario "sofia" autenticada con rol "Engineer"
    When "sofia" intenta crear el rol "Auditor"
    Then la operación es denegada con error "Permiso denegado"

  Scenario: Modificar nombre o descripción de un rol
    Given existe el rol "TempRole" con descripción "temporal"
    When el admin actualiza la descripción de "TempRole" a "rol temporal actualizado"
    Then la descripción de "TempRole" es "rol temporal actualizado"

  Scenario: Eliminar un rol y verificar efecto en usuarios
    Given existe el rol "Obsolete" asignado a los usuarios "u1" y "u2"
    When el admin elimina el rol "Obsolete"
    Then los usuarios "u1" y "u2" ya no tienen el rol "Obsolete"
    And las decisiones de acceso se recalculan según los roles restantes

  Scenario Outline: Asignar y quitar roles a usuarios
    Given existe un usuario "<user>"
    When el admin asigna el rol "<role>" a "<user>"
    Then "<user>" tiene el rol "<role>"
    When el admin remueve el rol "<role>" de "<user>"
    Then "<user>" no tiene el rol "<role>"

    Examples:
      | user  | role      |
      | pedro | Supervisor|
      | ana   | Accounting|
