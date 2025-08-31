Feature: Gestión de políticas de acceso (Rol + Tag -> Permisos)
  Como administrador del sistema de producción
  Quiero definir políticas que traduzcan (rol, tag) en permisos
  Para que el acceso a los módulos y datos productivos sea controlado centralmente

  Background:
    Given existe un usuario "admin" con rol "Admin" y está autenticado
    And existe el rol "Contabilidad"
    And existe el rol "Ingeniería"
    And existe la etiqueta "financiero"
    And existe la etiqueta "interno"
    And existe el módulo "Órdenes de Producción" etiquetado con "financiero"

  Scenario: Crear política que permita lectura a Contabilidad sobre datos financieros
    When el admin crea una política: rol "Contabilidad" + tag "financiero" => permisos [read]
    Then la política existe y aplica a (Contabilidad, financiero) con permiso "read"

  Scenario: Actualizar política para añadir escritura
    Given existe la política rol "Contabilidad" + tag "financiero" => [read]
    When el admin actualiza la política añadiendo "write"
    Then la política para (Contabilidad, financiero) incluye "write"

  Scenario: Borrar política revoca accesos efectivos
    Given existe la política rol "Ingeniería" + tag "interno" => [read]
    And existe un usuario "carlos" con rol "Ingeniería"
    And existe el módulo "Flujos de Producción" etiquetado con "interno"
    When el admin elimina la política (Ingeniería, interno)
    Then "carlos" ya no puede leer el módulo "Flujos de Producción"

  Scenario: Políticas afectan múltiples objetos etiquetados
    Given existen módulos "Órdenes de Producción", "Inventario" y "MRP" etiquetados con "financiero"
    And rol "Contabilidad" tiene permiso "read" sobre "financiero"
    When el admin añade permiso "write" a la política (Contabilidad, financiero)
    Then todos los módulos "Órdenes de Producción", "Inventario" y "MRP" permiten "write" para usuarios con rol "Contabilidad"
