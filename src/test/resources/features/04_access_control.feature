Feature: Comprobación de control de acceso efectivo en módulos del sistema
  Como sistema de planificación de producción
  Quiero verificar que la combinación (usuario.roles × módulo.tags × políticas) produce permisos correctos

  Background:
    Given existe un usuario "admin" con rol "Admin" y está autenticado
    And el admin ha creado rol "Contabilidad" y rol "Producción"
    And el admin ha creado la etiqueta "financial-data" con propietario "Contabilidad"
    And el admin ha creado la política (Contabilidad, financial-data) => [read, write]
    And existe el módulo "Facturación" con etiqueta "financial-data"

  Scenario: Usuario con rol contable puede acceder a módulo financiero
    Given existe un usuario "lucas" con rol "Contabilidad" y está autenticado
    When "lucas" intenta leer el módulo "Facturación"
    Then el acceso es permitido

    When "lucas" intenta escribir en el módulo "Facturación"
    Then el acceso es permitido

  Scenario: Usuario de otro rol no puede acceder a módulo sin permisos
    Given existe un usuario "ana" con rol "Producción" y está autenticada
    When "ana" intenta leer el módulo "Facturación"
    Then el acceso es denegado

  Scenario: Cambio de política afecta inmediatamente
    Given existe un usuario "pepito" con rol "Contabilidad" autenticado
    And la política (Contabilidad, financial-data) incluye "read"
    When el admin quita el permiso "read" de (Contabilidad, financial-data)
    Then "pepito" ya no puede leer el módulo "Facturación"

  Scenario: Propietario de tag puede etiquetar módulos, pero no modificar políticas sin permiso
    Given la etiqueta "produccion-data" es propiedad del rol "Producción"
    And existe un usuario "alice" con rol "Producción" autenticada
    When "alice" etiqueta el módulo "Inventario de Materiales" con "produccion-data"
    Then la operación es permitida

    Given que solo el rol "Seguridad" puede modificar políticas
    When "alice" intenta crear o modificar la política (Producción, produccion-data)
    Then la operación es denegada con error "Permiso denegado: modificar políticas"
