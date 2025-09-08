Feature: Gestión de Roles

  Background:
    Given estoy autenticado como "admin" con rol "Admin"

  Scenario: Crear un nuevo rol
    When envío una petición POST a "/api/roles" con el cuerpo:
      """
      {
        "name": "Accounting",
        "permissionIds": [1, 2]
      }
      """
    Then la respuesta debe tener el código de estado 201
    And el cuerpo de la respuesta debe contener los detalles del rol "Accounting"

  Scenario: Obtener todos los roles
    When envío una petición GET a "/api/roles"
    Then la respuesta debe tener el código de estado 200
    And el cuerpo de la respuesta debe ser una lista de roles

  Scenario: Actualizar un rol
    Given existe un rol con id 2
    When envío una petición PUT a "/api/roles/2" con el cuerpo:
      """
      {
        "name": "Finance",
        "permissionIds": [1, 2, 3]
      }
      """
    Then la respuesta debe tener el código de estado 200
    And el cuerpo de la respuesta debe contener el nombre actualizado "Finance"

  Scenario: Eliminar un rol
    Given existe un rol con id 3
    When envío una petición DELETE a "/api/roles/3"
    Then la respuesta debe tener el código de estado 204