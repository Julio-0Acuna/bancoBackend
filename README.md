# bancoBackend

# 🏦 Sistema Bancario en Java (POO + MySQL)

Sistema bancario de consola desarrollado en Java aplicando principios de Programación Orientada a Objetos (POO) e integración con base de datos MySQL mediante JDBC. Incluye autenticación, gestión de cuentas, transacciones y reportes.

## 🚀 Funcionalidades

- 🔐 Sistema de autenticación (Login)
- 🧾 Creación de cuentas bancarias
- 💰 Depósitos
- 💳 Retiros
- 📊 Consulta de saldo
- 📜 Historial de transacciones
- 🔎 Visualización de últimas N transacciones
- 📈 Reporte general de transacciones
- 🏗 Arquitectura en capas (Modelo, DAO, Servicio, UI)

## 🧠 Arquitectura del Proyecto

El proyecto sigue una estructura organizada por capas:

- `bd` → Conexión a base de datos
- `modelo` → Clases entidad (Cuenta, Transaccion, Usuario)
- `dao` → Acceso a datos (consultas SQL)
- `servicio` → Lógica de negocio
- `ui` → Interfaz de usuario en consola
- `Principal` → Punto de entrada del sistema

## 🛠 Tecnologías Utilizadas

- Java
- MySQL
- JDBC
- NetBeans

## 🗄 Estructura de Base de Datos

Tablas utilizadas:

- `usuarios`
- `cuentas`
- `transacciones`

## 🔑 Usuario por defecto

Usuario: `admin`  
Clave: `1234`

## 🎯 Objetivos de Aprendizaje

- Aplicar Programación Orientada a Objetos en Java
- Implementar arquitectura en capas
- Integrar Java con MySQL mediante JDBC
- Manejar validaciones y excepciones
- Desarrollar un proyecto backend estructurado

---

⚠️ Proyecto desarrollado con fines educativos y de portafolio profesional.
