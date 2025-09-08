Feature: Gestión de Políticas

  Background:
    Given estoy autenticado como "admin" con rol "Admin"

  Scenario: Crear una nueva política
    When envío una petición POST a "/api/policies" con el cuerpo:
      """
      {
        "roleId": 1,
        "tagId": 1,
        "permissions": ["READ", "WRITE"]
      }
      """
    Then la respuesta debe tener el código de estado 201
    And el cuerpo de la respuesta debe contener los detalles de la política creada

  Scenario: Obtener todas las políticas
    When envío una petición GET a "/api/policies"
    Then la respuesta debe tener el código de estado 200
    And el cuerpo de la respuesta debe ser una lista de políticas

  Scenario: Eliminar una política
    Given existe una política con id 2
    When envío una petición DELETE a "/api/policies/2"
    Then la respuesta debe tener el código de estado 204