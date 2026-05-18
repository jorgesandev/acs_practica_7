# Práctica 7: Implementación de Page Object Model (POM) en Selenium

**Universidad Autónoma de Baja California**  
**Facultad de Ciencias Químicas e Ingeniería**  
**Ingeniero en Software y Tecnologías Emergentes**

**Materia:** Aseguramiento de la Calidad de Software (QA)  
**Docente:** Luis Eloy Lazcano Ortiz

**Alumnos:**
- Jonathan David Martinez Espinoza — 1280192
- Jorge Alejandro Sandoval Romo — 1288599

**Fecha:** 17 de mayo de 2026

---

Proyecto de automatización de pruebas funcionales para la aplicación web [Parabank](https://parabank.parasoft.com/), aplicando el patrón de diseño **Page Object Model (POM)** con Selenium WebDriver y TestNG.

## Objetivo

Aplicar el patrón POM para estructurar pruebas automatizadas, separando la lógica de interacción con la interfaz (Page Objects) de la lógica de pruebas (Test Cases). Esto permite:

- Reutilización de código
- Mayor legibilidad
- Mejor mantenimiento
- Escalabilidad del framework

## Tecnologías

- Java 17+
- Selenium 4.31.0
- TestNG 7.11.0
- WebDriverManager 5.9.3
- Maven

## Estructura del proyecto

```
acs_practica_7/
├── pom.xml
├── src/
│   └── test/
│       ├── java/
│       │   ├── base/
│       │   │   └── BaseTest.java          # Configuración del WebDriver
│       │   ├── pages/
│       │   │   ├── LoginPage.java         # Página de inicio de sesión
│       │   │   ├── HomePage.java          # Dashboard principal
│       │   │   └── RegisterPage.java      # Página de registro de usuario
│       │   └── tests/
│       │       ├── LoginTest.java         # Casos de prueba de login/logout
│       │       └── RegisterTest.java      # Casos de prueba de registro
│       └── resources/
│           └── testng.xml                 # Suite de ejecución
```

## Cómo ejecutar

Desde la raíz del proyecto, compilar y ejecutar los tests con Maven:

```bash
mvn clean test
```

O ejecutar directamente el suite de TestNG desde IntelliJ IDEA seleccionando el archivo `src/test/resources/testng.xml`.

## Consideraciones

- Los tests se ejecutan contra el sitio de demostración **Parabank** (`https://parabank.parasoft.com/`).
- Las credenciales de demo válidas son `john` / `demo`.
- El test de registro genera datos únicos dinámicamente (timestamp) para evitar conflictos por usuarios o SSN duplicados en la base de datos de demostración.
