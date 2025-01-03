# Game-Vault
Aplicación para gestionar un catalogo de videojuegos.

## Objectivo
Brindar una App con interfaz intuitiva que gestione de manera eficiente el catálogo de videojuegos 
descargados y permita a los usuarios interactuar con él.

## Funciones
- **Buscar**
- **Ver detalles**
- **Editar**
- **Eliminar**

---

## Detalles de la API
- **URL base:** [FreeToGame API](https://www.freetogame.com/api)
- **Endpoint:** /games
- **Ejemplo de respuesta:**
  ```json
  [
  {
    "id": 582,
    "title": "Tarisland",
    "thumbnail": "https://www.freetogame.com/g/582/thumbnail.jpg",
    "short_description": "A cross-platform MMORPG developed by Level Infinite and Published by Tencent.",
    "game_url": "https://www.freetogame.com/open/tarisland",
    "genre": "MMORPG",
    "platform": "PC (Windows)",
    "publisher": "Tencent",
    "developer": "Level Infinite",
    "release_date": "2024-06-22",
    "freetogame_profile_url": "https://www.freetogame.com/tarisland"
  },
  ...
  ]
  ```
  
---

## Flujo de la app

### Paso 1: Carga inicial
- Descargar el catálogo de videojuegos desde el API.
- Guardado de la lista en una base de datos local.

### Step 2: Búsqueda y Filtros
- **Pantalla:** Lista de videojuegos.
    - **Mostrar lista de juegos descargados:**
      - No mostrar los videojuegos eliminados.
    - **Filtrar resultados por:**
        - Nombre del videojuego.
        - Categoria del videojuego.

### Step 3: Video Game Details
- **Pantalla:** Detalles del videojuego
    - Mostrar los detalles del videojuego seleccionado.
    - Eliminar videojuego
    - Editar detalles del videojuego

---

## Librerías

### Red
- **Retrofit2:** 2.11.0
- **Gson:** 2.11.0

### Almacenamiento local
- **Room:** 2.6.11

### Inyección de dependencias
- **Dagger Hilt:** 1.4.0

### Testing
- **Mockito:** 4.11.0
- **Mockito Kotlin:** 4.11.0

### Annotation Processor
- **KSP:** 2.0.21-1.0.28

### Otras por definir
- TODO

---

## Justificación técnica

### Arquitectura
Para la arquitectura trabajeré con _MVVM_ así la UI solo se encargará de presentar información y
todo lo que corresponde a la logica del negocio podrá ser separada en archivos más pequeños.

Para la estrcutura del proyecto he seleccionado _Clean Architecture_ para organizar el código y sus 
archivos en diferentes capas, a su vez me permitirá llevar una separación adecuada de 
responsabilidad y funcionará como guía de la interacción entre capas al practicar la _inyección 
de dependencias_.

```
proyecto/
+-- app(ui)/
|
+-- domain/
|
+-- data/ 

```

Otra de las ventajas es que facilitará la creación de pruebas unitarias al tener que probar 
unicamente las funciones del archivo.

Durante el desarrollo seguiré los principios _SOLID_, hay que considerar que SOLID funciona como una 
guía y en ocasiones se debe ser flexible en la implementación de sus principios en pro de la 
funcionalidad.


### Patrones de diseño y comportamiento
- **Repository:** Separaré el origen de los datos de la lógica, así será más sencillo modificar las
capas de la app sin afectar a la otra.
- **Adapter:** Mapear objetos de una capa de la app para que puedan viajar a otra capa.
- **Observer:** Acompaña MVVM para transmitir datos de los ViewModel a la UI.
- **Dependency injection:** Inicializaré las clases requeridas por otras en un origen y así facilitar
 la implementación de SOLID. También mejora el testing de las clases al facilitar los mocks.
- **Delegate:** Distribuire la carga de algunas clases en otras para que estas sean mas granulares y
evitar la creación de clases muy grandes.

### UI
Elegí _Jetpack Compose_ ya que facilita el desarrollo de interfaces así como su implementación 
y previsualización. Por otro lado es la herramienta que actualmente recomienda Google para el 
desarrollo de vistas en aplicaciones Android.

---

## Notas

- Aunque este documentoe stá escritó en español, el desarrollo y comentarios en el código estarán
escritos en inglés, de esta manera hay uniformidad y se considera una buena práctica de desarrollo.

