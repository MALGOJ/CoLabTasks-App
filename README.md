# CoLabTasks-App

CoLabTasks-App es una aplicación móvil desarrollada en lenguaje de programación **Kotlin** para dispositivos Android. Está diseñada para ser una herramienta integral de administración de tareas, permitiendo a los usuarios gestionar sus actividades de manera colaborativa y eficiente. Los usuarios pueden **crear, actualizar, eliminar y listar tareas**, así como asignar **prioridades y estados** a cada una de ellas. Además, cada tarea puede ser asignada a un proyecto específico, facilitando la organización y el seguimiento de actividades dentro de diferentes contextos de trabajo.

---

## Principales funcionalidades

- **Gestión de tareas**: Los usuarios pueden gestionar sus tareas mediante funcionalidades como crear nuevas tareas, editarlas, eliminarlas o marcarlas como completadas.
- **Organización por proyectos**: Cada tarea puede ser asignada a un proyecto para facilitar la agrupación y el seguimiento.
- **Asignación de prioridades y estados**: Clasifica las tareas según su importancia o estado (pendiente, en progreso, completada).
- **Autenticación segura**: La aplicación garantiza el acceso seguro mediante un sistema de autenticación robusto basado en JWT (JSON Web Tokens).
- **Sincronización en tiempo real**: Los datos de las tareas y proyectos se sincronizan continuamente con el backend.
- **Temas personalizados**: Ofrece temas claros y oscuros para adaptarse a las preferencias del usuario.
- **Almacenamiento cifrado**: Los tokens JWT se almacenan localmente usando **cifrado avanzado**, garantizando la privacidad y la seguridad de la información del usuario.
- **Notificaciones inteligentes**: Recibe alertas sobre fechas de vencimiento, actualizaciones de proyectos o tareas asignadas.
- **Diseño modular y extensible**: Construida con una arquitectura moderna para facilitar futuras expansiones.

---

## Tecnologías y herramientas utilizadas

### Jetpack Compose
CoLabTasks-App está diseñada utilizando **Jetpack Compose**, lo que permite ofrecer una experiencia de usuario moderna, fluida y altamente personalizable.
- **Scaffold personalizado**: Cada sección de la aplicación utiliza un `Scaffold` adaptado para maximizar la funcionalidad y el diseño visual.
- **Temas personalizados**: Incluye temas claros y oscuros que se pueden cambiar dinámicamente desde la configuración de la aplicación.
- **Animaciones fluidas**: Implementa transiciones animadas entre pantallas y cambios de estado.

### Navigation Component
La navegación entre pantallas se gestiona con la librería `androidx.navigation.compose`, lo que permite:
- **Navegación declarativa**: Define rutas de manera clara y estructurada.
- **Transiciones animadas**: Mejora la experiencia del usuario al navegar entre diferentes pantallas.
- **Gestión eficiente de la pila de navegación**: Facilita la gestión de rutas complejas y flujos de usuario.

### Room Database
Para la persistencia de datos locales, la aplicación utiliza **Room**, una capa de abstracción sobre SQLite que permite:
- **Definición de entidades y DAOs**: Modela y accede a datos mediante un enfoque orientado a objetos.
- **Consultas optimizadas**: Realiza consultas SQL con un rendimiento mejorado.
- **Migraciones fáciles**: Maneja actualizaciones de esquema sin complicaciones.

### Retrofit
La comunicación con el backend se realiza con **Retrofit**, una potente librería que facilita:
- **Consumo de APIs REST**: Conexión eficiente a servicios web mediante métodos HTTP (GET, POST, PUT, DELETE).
- **Manejo de respuestas**: Procesa respuestas en formato JSON para integrarlas con los datos de la aplicación.
- **Intercambio seguro de información**: Autenticación y validación mediante tokens JWT.

### KSP (Kotlin Symbol Processing)
Se utiliza **KSP** para:
- **Generación automática de código**: Simplifica la creación de DAOs, modelos y clases auxiliares.
- **Procesamiento de anotaciones**: Optimiza el desarrollo reduciendo la necesidad de código repetitivo.

### Kotlin Coroutines
La programación asincrónica se maneja con **kotlinx.coroutines**, permitiendo:
- **Tareas en segundo plano**: Evita bloqueos en la interfaz de usuario.
- **Flujos reactivos**: Maneja actualizaciones en tiempo real.
- **Mejora en la capacidad de respuesta**: Asegura una experiencia fluida, incluso en operaciones complejas.

### Seguridad
- **Cifrado avanzado**: Los tokens JWT se almacenan cifrados utilizando algoritmos modernos como AES.
- **Manejo seguro de sesiones**: Los usuarios inactivos son desconectados automáticamente después de un período de inactividad.

---

## Descripción de la aplicación

### Pantallas principales
#### **Pantalla de inicio de sesión**
- Permite a los usuarios autenticarse utilizando sus credenciales.
- Incluye validaciones dinámicas para contraseñas y nombres de usuario.
- Animaciones fluidas al mostrar mensajes de error.

#### **Pantalla de registro**
- Proporciona un formulario intuitivo para que los nuevos usuarios puedan registrarse.
- Validación automática de campos como correo electrónico, contraseñas y nombres únicos.

#### **Pantalla de lista de tareas**
- Muestra todas las tareas asignadas al usuario en un diseño limpio y ordenado.
- Filtros por estado, prioridad y proyecto.
- Permite acciones rápidas como marcar tareas como completadas o eliminar tareas.

#### **Pantalla de detalle de tarea**
- Proporciona una vista detallada de una tarea, incluyendo su descripción, fecha de vencimiento, prioridad y estado.
- Permite editar todos los campos de la tarea en tiempo real.

#### **Pantalla de gestión de proyectos**
- Lista todos los proyectos creados por el usuario, mostrando estadísticas como número de tareas pendientes.
- Incluye la funcionalidad de agregar, editar o eliminar proyectos.

#### **Configuración**
- Cambia el tema (claro/oscuro).
- Permite cerrar sesión de forma segura.
- Opciones avanzadas para personalización de notificaciones.

---

## Librerías utilizadas

- **Retrofit**: Comunicación eficiente con APIs REST.
- **Room**: Persistencia de datos local optimizada.
- **KSP**: Generación de código en tiempo de compilación.
- **Kotlin Coroutines**: Programación asincrónica y concurrente.
- **Jetpack Compose**: Interfaz moderna y declarativa.
- **Navigation Component**: Navegación estructurada y eficiente.

---

### Contribuciones
Si deseas contribuir a CoLabTasks-App, asegúrate de seguir las mejores prácticas para Kotlin y Jetpack Compose. Las solicitudes de extracción (pull requests) son bienvenidas.

---

## Funcionalidades a perfeccionar

- **Asignación a proyectos**: Mejorar la funcionalidad para que los usuarios puedan asignar tareas de manera más eficiente a proyectos específicos, asegurando una mayor flexibilidad y usabilidad.
- **Grupos de trabajo**: Implementar la posibilidad de crear grupos de trabajo que puedan gestionar múltiples proyectos y tareas de manera colaborativa. Los usuarios podrán integrarse a estos grupos según sea necesario.
- **Notificaciones programadas**: Optimizar el sistema de notificaciones para que los usuarios puedan configurar alertas programadas relacionadas con fechas de vencimiento, actualizaciones de tareas y recordatorios importantes.

---

### Contacto
Para más información, contáctanos en: **contact@colabtasks-app.com**.


