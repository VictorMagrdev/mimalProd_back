Feature: Control de Acceso a Endpoints Protegidos

  Scenario: Usuario con permisos puede acceder a un recurso protegido
    Given un usuario "editor" tiene el rol "Editor"
    And el rol "Editor" tiene permiso para "CREATE" en "TAG_USERS"
    And "editor" está autenticado
    When envío una petición POST a "/api/users" con un cuerpo válido
    Then la respuesta debe tener el código de estado 201

  Scenario: Usuario sin permisos no puede acceder a un recurso protegido
    Given un usuario "viewer" tiene el rol "Viewer"
    And el rol "Viewer" NO tiene permiso para "CREATE" en "TAG_USERS"
    And "viewer" está autenticado
    When envío una petición POST a "/api/users" con un cuerpo válido
    Then la respuesta debe tener el código de estado 403

  Scenario: Un cambio de política de acceso se refleja inmediatamente
    Given un usuario "manager" tiene el rol "Manager"
    And el rol "Manager" tiene permiso para "DELETE" en "TAG_ROLES"
    And "manager" está autenticado
    And existe un rol con id 10
    When envío una petición DELETE a "/api/roles/10"
    Then la respuesta debe tener el código de estado 204
    And el rol "Manager" pierde el permiso para "DELETE" en "TAG_ROLES"
    And existe un rol con id 11
    When envío una petición DELETE a "/api/roles/11"
    Then la respuesta debe tener el código de estado 403