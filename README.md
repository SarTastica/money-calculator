# money-calculator

Un robusto conversor de divisas desarrollado en **Java** (Swing). Este proyecto implementa el patrón **Modelo-Vista-Controlador** y sigue los principios de **Arquitectura Limpia**.

## Características Clave

* **En línea y sin conexión:** Obtiene los tipos de cambio en tiempo real desde una API, pero cambia automáticamente a un archivo local (`exchangerates.json`) si no hay internet.
* **Arquitectura:** Implementa **Patrón de Comandos** e **Inyección de Dependencias**.
* **Tipos de cambio cruzados:** Calcula los tipos de cambio entre dos divisas cualesquiera (por ejemplo, USD a GBP) utilizando EUR como base.
* **Pruebas:** Incluye pruebas unitarias y de integración (JUnit).

## Estructura

* `model`: Entidades (Dinero, Moneda).
* `view`: Interfaces de usuario.
* `swing`: Implementación de la interfaz gráfica de usuario.
* `control`: Implementación de comandos.
* `io`: API y cargadores de archivos.
