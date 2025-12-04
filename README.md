# AgroVerdeSPAappMdos

Integrantes : Nico Maturana y Andy Navarrete 
- Tomamos el caso agroverde que es un ecomarket.

Nosotros tomamos el Proyecto de AgoverdeSPA para dar un inicio a la venta y distribucion de productos agricolas que la mayoria son organicos y que la app contara con distintas partes como perfil de usuario  y un registro basicamente .

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
- Persistencia Local: DataStore (para SessionManager)
- Backend: MongoDB Atlas + Render
 
### Instalación:
Primero que deben abrir su gitHub y clonar el repositorio : https://github.com/Nimaturana/AgroVerdeSPAMovil.git 
-Luego Abrir el proyecto en android Studio
-Y luego esperar que la URL sincronice las dependencias.

- Ejecución: Para tener una ejecución de la app es sencillo inicia desde un emulador Android deberás tener el proyecto extraído de tu github y veras después una vez carhado todo vera en la parte de arriba te debe aparecer una felchita o botón verde el cual dice Run app le das y ya estaría compilando para instalar la app.

 ### Arquitectura y flujo del Proyecto:

 ### Proceso basico 
 Registro -> Validacion -> Login -> Home -> Perfil 
 ### Imagenes de la estructura de proyecto.
 - <img width="390" height="887" alt="image" src="https://github.com/user-attachments/assets/c9ba4c03-3897-4a3c-b168-f1dd829b587a" />
 -   <img width="390" height="681" alt="image" src="https://github.com/user-attachments/assets/5ac748c4-c378-45d6-8773-4bc0c91004c6" />






 

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


### Aqui tenemos el Home despues de haber iniciado sesion el cual enseña la opcion de ir a Perfil -> Productos -> Ver Clima de mayor acceso facil y al pie de la vista un Cerrar Sesion.
<img width="200" height="792" alt="image" src="https://github.com/user-attachments/assets/dae66ff6-2e38-4467-8778-d9599580e9c2" />

### Aqui tenemos la vista de productos disponibles son su barra busqueda , categoria , precio minimo - precio maximo en un listado de items.
<img width="200" height="736" alt="image" src="https://github.com/user-attachments/assets/80c2a0b2-0a3e-4ad3-8b39-900c0d3d733c" />


### Aqui tenemos la pantalla de Perfil que tiene acceso a camara e info de la cuenta con los datos.
<img width="200" height="920" alt="image" src="https://github.com/user-attachments/assets/4606f7b0-9925-4dbe-993b-3e17a8d5bd40" />



# Funcionalidades
 
- Un Register de usario que sirve para crear cuentas para nuevo usuarios que se llaman RegisterScreen.kt y también el RegisterViewModel que ahora sumamos la ayuda de ValidationUtils que nos sirve para revisar que el correo y la contraseña esten validos o correctos, si esta todo bien se guarda el usuario en el registro usando el UserDataStore.
 
- También contamos con un (ValidationUtils.kt) de datos el cual dice que sin los datos ingresados sean validos y cumplan requisitos que estos van conectados con UserDataStore.
 
- Un Basico Login (LoginScreen.kt y LoginViewModel) es especial para ya usuarios registrados lo que hace es revisar al momento si escribiste mal tu cuenta que los compara los datos registrados con los actuales guardados que estan en UserDataStore y si alguno de los datos que ingresamos no coincide es porque el User no existe mostrando un error.
 
- Un Gestion de sesión llamado (SessionManager) que esta puesto en la carpeta Local y la función que hace es recordar al usuario que ya este registrado.
 
- Trae un (HomeScreen.kt y un HomeViewModel) es la pantalla que sale después de dar inicio sesión mejor dicho el menú general y que se encarga de traerte el clima.
  
- ProductosViewModel Controla el catálogo de productos Su función principal es conectar con el ProductoRepository para listar los productos pero además incluye un sistema de filtros que nos  permite buscar en tiempo real por nombre, categoría y rango de precios,ascendente y descendente actualizando la lista automáticamente sin recargar la pantalla.
  
- Perfil Basico llamado (ProfileScreen.kt) que ese seria el perfil del usario donde se muestra la info que se puso al crear la cuenta. Tambien cuenta con AvatarRepository que nos ayuda a guardar la foto o la que tomaste con la camara.

Apartado más especificado de 
### Formulario validado (registro/otra entidad)
- Implementamos un RegisterScreen.kt con un RegisterViewModel para la creacion de las cuentas de usuarios.
- Lo importante es que usamos ValidationUtils.kt para asegurar que los datos sean reales ppara que se guarden despues  revisamos que no haya campos vacíos, que el email tenga el formato correcto y que la contraseña cumpla con el largo mínimo.
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
  
