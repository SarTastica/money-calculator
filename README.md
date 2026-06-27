---
title: "Money Calculator - Proyecto de Ingeniería del Software II"
author: "Salwa Madani Lazaar"
---

# Money Calculator

Este repositorio contiene una aplicación de escritorio desarrollada en **Java Swing** para la conversión de divisas. El proyecto ha sido diseñado priorizando la **Arquitectura Limpia**, la modularidad y la experiencia de usuario (UX).

---

## Guía de Ejecución

Para iniciar la aplicación, asegúrate de tener configurado el entorno de **Maven**:

1. **Dependencias:** Asegúrate de que el archivo `pom.xml` incluya la librería `flatlaf` para el diseño moderno.
2. **Ejecución:** Ejecuta la clase `Main.java` ubicada en:
   `src/main/java/software/ulpgc/moneycalculator/application/Main.java`

---

## Características Principales

* **UI Moderna (FlatLaf):** Interfaz rediseñada con componentes modernos, esquinas redondeadas y un estilo *Card UI* profesional.
* **Sistema de Intercambio (Swap):** Funcionalidad integrada para invertir instantáneamente las divisas de origen y destino mediante un comando dedicado.
* **Patrón de Comandos:** Lógica de negocio desacoplada de la interfaz gráfica, permitiendo registrar y ejecutar acciones (como "Exchange" o "Swap") sin acoplar los componentes visuales.
* **Resiliencia:** Gestión de errores mediante *feedback* visual al usuario (JOptionPane), evitando cierres inesperados de la aplicación.
* **Inyección de Dependencias:** El punto de entrada (`Main.java`) actúa como *Composition Root*, facilitando el intercambio de fuentes de datos (API vs. Mock) sin modificar la lógica principal.

---

## Arquitectura y Diseño

El proyecto sigue estrictamente los principios **SOLID** y el patrón de diseño **MVP (Model-View-Presenter)**/Comandos.

### Capas del sistema
* **Control:** Implementación de acciones mediante el `Command Pattern`. Esto permite que el `MainFrame` sea "agnóstico" respecto a qué hace cada botón; solo delega la ejecución al comando registrado.
* **Model:** Entidades inmutables (`Currency`, `Money`, `ExchangeRate`) que aseguran la consistencia de los datos.
* **View (Swing):** Capa de presentación. Hemos separado la lógica de UI de la lógica de negocio mediante interfaces (ej. `MoneyDialog`, `CurrencyDialog`), cumpliendo el **Principio de Inversión de Dependencias (DIP)**.

### Estructura de directorios
```text
src/main/java/software/ulpgc/moneycalculator/
├── application/  # Composition Root (Main.java)
├── control/      # Lógica de los Comandos
├── io/           # Interfaces de persistencia y API Loaders
├── model/        # Entidades del dominio
├── swing/        # Implementación de la vista (UI)
└── ui/           # Contratos/Interfaces de la vista
```

## Stack Tecnológico

| Tecnología | Propósito |
| :--- | :--- |
| **Java 17+** | Lenguaje principal |
| **Swing** | Framework de interfaz gráfica |
| **FlatLaf** | Librería de estilos y diseño moderno |
| **GSON** | Serialización y parseo de JSON (APIs) |
| **Maven** | Gestión de dependencias y compilación |

## Conclusión
Este proyecto demuestra cómo aplicar principios de ingeniería de software para crear aplicaciones mantenibles. La separación de componentes mediante interfaces y la implementación del patrón **Command** aseguran que, en el futuro, se puedan añadir nuevas funcionalidades (como gráficos históricos o nuevas APIs) sin necesidad de reescribir la lógica existente.
