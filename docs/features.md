# Funcionalidades de la aplicación

## Funcionalidades del usuario

PassGo permite a los usuarios gestionar de forma segura sus credenciales digitales mediante las siguientes funcionalidades:

1. ✔️ Registrarse en la aplicación mediante la creación de una cuenta personal.  
2. ✔️ Iniciar sesión de forma segura utilizando sus credenciales.  
3. ✔️ Cerrar sesión para proteger el acceso a su información.  
4. ✔️ Agregar nuevas credenciales asociadas a sitios web o aplicaciones.  
5. ✔️ Editar la información de credenciales almacenadas.  
6. ✔️ Eliminar credenciales cuando ya no sean necesarias.  
7. ✔️ Visualizar la lista de credenciales guardadas.  
8. ✔️ Buscar credenciales por nombre de sitio o servicio.  
9. ✔️ Generar contraseñas seguras automáticamente.  
10. ✔️ Mostrar u ocultar la contraseña almacenada.  
11. ✔️ Copiar la contraseña al portapapeles para facilitar su uso.  
12. ✔️ Validar el nivel de seguridad de una contraseña ingresada.  
13. ✔️ Confirmar acciones sensibles como eliminación de credenciales.  
14. ✔️ Mantener las contraseñas almacenadas de forma protegida.  
15. ✔️ Organizar las credenciales para facilitar su consulta y gestión.  

## Restricciones técnicas

1. **Compatibilidad Android:** La aplicación debe ser compatible con una versión mínima de Android (por ejemplo, **Android 8.0 Oreo / API 26** o superior), de modo que pueda ejecutarse en la mayoría de dispositivos actuales.

2. **Arquitectura del proyecto:** Se debe mantener una arquitectura organizada (por ejemplo, **MVVM o MVC**), separando lógica de negocio, UI y acceso a datos para facilitar mantenimiento y pruebas.

3. **Almacenamiento local:** Las credenciales deben persistirse en el dispositivo usando una solución local definida (por ejemplo **Room/SQLite**). No se permitirá depender obligatoriamente de internet para funcionar.

4. **Seguridad de datos:** Las contraseñas no deben almacenarse en texto plano. Se debe aplicar al menos una capa de protección (por ejemplo cifrado local o uso de **Android Keystore** para proteger claves).

5. **Acceso y autenticación:** La aplicación debe exigir **inicio de sesión** para acceder a las credenciales. No se permite que la información quede visible sin autenticación.

6. **Permisos mínimos:** La app solo debe solicitar permisos estrictamente necesarios. Si se usa “copiar al portapapeles”, no requiere permisos especiales, pero se debe manejar de forma segura.

11. **Control de versiones:** El código debe mantenerse en **GitHub**, con commits frecuentes y mensajes claros. Se deben excluir archivos generados (build/, .idea/, etc.).

12. **Rendimiento:** La aplicación debe funcionar correctamente en dispositivos de gama media, evitando cargas pesadas en el hilo principal (por ejemplo, consultas a base de datos deben ejecutarse fuera del UI thread).
