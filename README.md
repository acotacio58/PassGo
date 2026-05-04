# 🔐 PassGo - Gestor de Contraseñas

PassGo es una aplicación móvil desarrollada en Android que permite a los usuarios gestionar, generar y organizar contraseñas de forma segura y sencilla.

## 📱 Descripción

PassGo funciona como una bóveda digital personal donde los usuarios pueden:

- Guardar contraseñas organizadas por categorías
- Generar contraseñas seguras
- Administrar su perfil
- Recuperar acceso mediante restablecimiento de contraseña

El proyecto está enfocado en ofrecer una interfaz moderna, intuitiva y alineada con buenas prácticas de desarrollo móvil.

---

## 🚀 Funcionalidades

### 🔐 Autenticación
- Inicio de sesión
- Registro de usuario
- Recuperación de contraseña (flujo completo)
  - Ingreso de correo
  - Confirmación de envío
  - Verificación por código

### 🏠 Pantalla Principal
- Vista general del usuario
- Acceso rápido a categorías
- Listado de contraseñas

### 🔑 Gestión de Contraseñas
- Organización por categorías:
  - Redes Sociales
  - Aplicaciones
  - Billetera
- Visualización de cuentas guardadas

### ⚙️ Generador de Contraseñas
- Longitud configurable
- Inclusión de:
  - Mayúsculas
  - Minúsculas
  - Números
  - Caracteres especiales
- Generación automática

### 👤 Perfil
- Visualización de datos del usuario
- Edición de información
- Cierre de sesión
- Eliminación de cuenta

---

## 🛠️ Tecnologías utilizadas

- Kotlin
- Jetpack Compose
- Android Studio
- Material Design 3
- Navigation Component

---

## 📂 Estructura del proyecto

```bash
ui/
 ├── screens/
 │   ├── auth/        (login, registro, recuperación)
 │   ├── home/
 │   ├── passwords/
 │   ├── profile/
 │   ├── generator/
 ├── theme/
navigation/
```

## 🎨 Diseño

El diseño de la aplicación fue realizado en Figma:

👉 https://www.figma.com/design/5o0LloKLvxA9a0R5p2F2FW/PassGo--Gestor-de-Contrase%C3%B1as-

---

## 🧪 Nota para pruebas

Para probar la aplicación es necesario registrarse primero.

Al momento del registro o recuperación de contraseña, al presionar “Abrir correo” se abrirá la aplicación de correo del dispositivo con un mensaje prellenado que incluye el código de verificación. Este código debe copiarse e ingresarse en la app para continuar.

La aplicación ya se encuentra operativa y cumple con los requerimientos principales; sin embargo, aún hay algunos detalles visuales y ajustes menores que serán mejorados en la próxima entrega.

---

## ⚙️ Instalación

1. Clonar el repositorio:

```bash
git clone https://github.com/acotacio58/PassGo.git
