# AgroVerdeSPAappMdos

Integrantes : Nico Maturana y Andy Navarrete 
- Tomamos el caso agroverde que es un ecomarket.

Caso: Agroverde que es una EcoMarket

Alcance EP3 tenemos un:
- RegisterScreen.kt
- AppNavigation.kt
- LoginUiState.kt y también ViewModel
- Recursos Nativos como : Cámara , galería llamado ImagePickerDialog.kt
- Sumar Api : Usamos la Api https://openweathermap.org/

## Stack:
- Lenguaje usado Kotlin
- Arquitectura MVVM
- Gestión Estado : UiState y el StateFlow
- Navegación usada es el JetPack Navigation
- Persistencia Local : DataStore (Usado para el SessionManager)
- Componentes Nativos que son Camata y Galeria llamados
 
### Instalación:
Primero que deben abrir su gitHub y clonar el repositorio : https://github.com/Nimaturana/AgroVerdeSPAMovil.git 
-Luego Abrir el proyecto en android Studio
-Y luego esperar que la URL sincronice las dependencias.

- Ejecución: Para tener una ejecución de la app es sencillo inicia desde un emulador Android deberás tener el proyecto extraído de tu github y veras después una vez carhado todo vera en la parte de arriba te debe aparecer una felchita o botón verde el cual dice Run app le das y ya estaría compilando para instalar la app.

 ### Arquitectura y flujo del Proyecto:

 ### Proceso basico 
 Registro -> Validacion -> Login -> Home -> Perfil 
 ### Adjunto Imagenes de la estructura 
 - <img width="391" height="927" alt="image" src="https://github.com/user-attachments/assets/f9588359-3477-480c-ac27-ce16e8985eb3" />
 - <img width="390" height="235" alt="image" src="https://github.com/user-attachments/assets/39e7e7f8-1961-4c5e-b0e5-b81825628279" />


 

### Recursos Nativos:
- Camara y galeria fueron integradas en ImagePickerDialog.kt
  
### Gestión de estado:
- El archivo que se llama modelo de vista que es llamado ( LoginViewModel.kt ) va de la mano de LoginUiState.kt que ese entrega la info en pantalla
- La pantalla lee todo lo que se entrega desde ese archivo modelo de vista.

### Navegación: stack/tabs/deep link,
- Usamos el archivo navegación/AppNavigation.kt que este archivo se usa para definir las conexiones que se hacen dentro de la app o pantallas
  
### Adjuntamos evidencia visual de la app

### Aqui podemos ver la pantalla de Inicio de Sesion si no tiene cuenta apreta registrar
<img width="200" height="922" alt="image" src="https://github.com/user-attachments/assets/45b5565d-f592-4957-98c7-c25c3d86c906" />


### Aqui vemos la pantalla de registro de usuario que da al no tener cuenta
<img width="200" height="923" alt="image" src="https://github.com/user-attachments/assets/b64bc624-521b-4d61-ac06-3f60628f4719" />


### Aqui tenemos el Home despues de haber iniciado sesion el cual enseña la opcion de ir a Perfil y este trae la API de clima junto a su boton.
<img width="200" height="921" alt="image" src="https://github.com/user-attachments/assets/a492fad4-7b75-42c7-b2c9-78d6432e8931" />


### Aqui tenemos la pantalla de Profile que tiene acceso a camara e info de la cuenta con los datos.
<img width="200" height="920" alt="image" src="https://github.com/user-attachments/assets/4606f7b0-9925-4dbe-993b-3e17a8d5bd40" />



# Funcionalidades
 
- Un Register de usario que sirve para crear cuentas para nuevo usuarios que se llaman RegisterScreen.kt y también el RegisterViewModel
 
- También contamos con un (ValidationUtils.kt) de datos el cual dice que sin los datos ingresados sean validos y cumplan requisitos
 
- Un Basico Login (LoginScreen.kt y LoginViewModel) es especial para ya usuarios registrados.
 
- Un Gestion de sesión llamado (SessionManager) que esta puesto en la carpeta Local y la función que hace es recordar al usuario que ya este registrado.
 
- Trae un (HomeScreen.kt y un HomeViewModel) es la pantalla que sale después de dar inicio sesión mejor dicho el menú general.
 
- Perfil Basico llamado (ProfileScreen.kt) que ese seria el perfil del usario donde se muestra la info que se puso al crear la cuenta.

Apartado más especificado de 
### Formulario validado (registro/otra entidad)
- Implementamos un RegisterScreen.kt con un RegisterViewModel para la creacion de las cuentas de usuarios.
- Tambien esta ValidationUtils.kt que implementamos para asegurar que los datos sean validos en los registros.
### Navegación y backstack
- Usamos AppNavigation.kt para gestionar los procesos de flujos de pantallas
- Cuando se hace Login se usan LoginScreen.kt y la app viaja hasta el HomeScreen.kt
### Gestión de estado (carga/éxito/error)
- el gestion de estado pertenece a los ViewModel.kt
- y en el UiState como el LoginUiState.kt hace que entregue el isLoading que es la barrita o circulito de carga o aveces puede mostrar que fallo algo.
### Persistencia local (CRUD) y almacenamiento de imagen de perfil
- Usamos un SessionManager para tener un registro persistencia local de guardar y leer.
### Recursos nativos: cámara/galería (permisos y fallback)
- En la app usamos algunos componentes nativos basicos que fueron los de galeria y camara que se encuentran en ImagePickerDialog.kt y ahi mismo poder elegir si tomarnos una foto o buscar en galeria.


### Consumo de API (incluye /me)

- API clima con su apikey de OpenWeatherMap
- Sitio Web: openweathermap.org


## Endpoints
<img width="900" height="79" alt="image" src="https://github.com/user-attachments/assets/6b6477d6-ce73-44f0-8144-45ba856f9b78" />

Base URL: (https://api.openweathermap.org/data/2.5/weather?q=Santiago&appid=97413f5c744202c514c51d7b858b16b1&units=metric&lang=es)

## User flows
### Procesos del Usuario al ingresar paso a paso:
- El usuario nuevo abre la App y llega a LoginScreen
- Llega a RegisterScreen y llena el formulario solicitado y presiona (Registar)
- La App lo verifica y se guardan los datos en token en SessionManager y se envia a HomeScreen.
- El Usuario presiona Perfil o ProfileScreen y puede cambiar foto seleccionando Camara 
- Toma la foto despues acepta y queda actualizada la foto de perfil de la cuenta.

### Casos que den errores:
- Error de Login : Puede ser que el user ingrese contraseña incorrecta y la app le muestra un mensajito que Credenciales Invalidas y no deja seguir
- Error en la Validacion : El user intenta registrar un mail invalido y se detecta y lanza un error diciendo "Email no valido".
- Error de Red: El usuario pierde conexion de wifi y si quiere guardar perfil arroja un mensaje diciendo "No se pudo conectar al servidor"
  
