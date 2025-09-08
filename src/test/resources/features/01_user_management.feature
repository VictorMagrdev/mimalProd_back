Feature: Gestión de Usuarios

  Background:
    Given estoy autenticado como "admin" con rol "Admin"

  Scenario: Crear un nuevo usuario
    When envío una petición POST a "/api/users" con el cuerpo:
      """
      {
        "username": "juan",
        "email": "juan@acme.com",
        "password": "password123",
        "roleIds": [1]
      }
      """
    Then la respuesta debe tener el código de estado 201
    And el cuerpo de la respuesta debe contener los detalles del usuario "juan"

  Scenario: Obtener todos los usuarios
    When envío una petición GET a "/api/users"
    Then la respuesta debe tener el código de estado 200
    And el cuerpo de la respuesta debe ser una lista de usuarios

  Scenario: Actualizar un usuario
    Given existe un usuario con id 2
    When envío una petición PUT a "/api/users/2" con el cuerpo:
      """
      {
        "email": "juan.perez@acme.com"
      }
      """
    Then la respuesta debe tener el código de estado 200
    And el cuerpo de la respuesta debe contener el email actualizado "juan.perez@acme.com"

  Scenario: Desactivar un usuario
    Given existe un usuario con id 3
    When envío una petición POST a "/api/users/3/deactivate"
    Then la respuesta debe tener el código de estado 200
    And el cuerpo de la respuesta debe contener el mensaje "Usuario desactivado"

  Scenario: Asignar un rol a un usuario
    Given existe un usuario con id 2 y un rol con id 3
    When envío una petición POST a "/api/users/2/roles" con el cuerpo:
      """
      {
        "roleId": 3
      }
      """
    Then la respuesta debe tener el código de estado 200

  Scenario: Remover un rol de un usuario
    Given un usuario con id 2 tiene asignado el rol con id 3
    When envío una petición DELETE a "/api/users/2/roles/3"
    Then la respuesta debe tener el código de estado 204