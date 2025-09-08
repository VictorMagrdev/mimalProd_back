Feature: Autenticación de Usuarios

  Scenario: Inicio de sesión exitoso
    Given un usuario existe con el nombre de usuario "admin" y la contraseña "AdminPass123"
    When envío una petición POST a "/api/auth/login" con el cuerpo:
      """
      {
        "username": "admin",
        "password": "AdminPass123"
      }
      """
    Then la respuesta debe tener el código de estado 200
    And el cuerpo de la respuesta debe contener un token JWT

  Scenario: Inicio de sesión con credenciales incorrectas
    Given un usuario existe con el nombre de usuario "admin" y la contraseña "AdminPass123"
    When envío una petición POST a "/api/auth/login" con el cuerpo:
      """
      {
        "username": "admin",
        "password": "wrongpassword"
      }
      """
    Then la respuesta debe tener el código de estado 401
    And el cuerpo de la respuesta debe contener el mensaje de error "Credenciales incorrectas"

  Scenario: Intento de inicio de sesión con un usuario inactivo
    Given un usuario inactivo existe con el nombre de usuario "inactiveuser" y la contraseña "password"
    When envío una petición POST a "/api/auth/login" con el cuerpo:
      """
      {
        "username": "inactiveuser",
        "password": "password"
      }
      """
    Then la respuesta debe tener el código de estado 401
    And el cuerpo de la respuesta debe contener el mensaje de error "Usuario inactivo o deshabilitado"