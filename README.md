<img src="https://i.imgur.com/6E4bFW7.png" width=100%>
<center>
<h1>Desafío Final Bootcamp | Grupo 3


## Descripción 📑
**Desarrollo con Spring Boot + Testing + JPA**
El objetivo de este desafío es aplicar los contenidos dados hasta el momento durante el BOOTCAMP (Git, Java, Spring Boot, Testing, JPA + Hibernate, Spring Security y JWT) en la implementación de una API REST a partir de un enunciado propuesto, una especificación de requisitos técnico-funcionales y documentación anexada. 

## Escenario 🖋

La agencia de turismo para la cual fue realizada la API en los desafíos Nº 1 y Nº 2 ha quedado encantada con todo nuestro trabajo. Dado esto, quieren llevar a cabo una implementación final que permita incorporar nuevas funcionalidades a la aplicación desarrollada como así también algunas medidas de seguridad para diferenciar los roles de cada uno de los usuarios que interactúen con el sistema.

Las nuevas funcionalidades e implementaciones estarán divididas en “Requerimientos Funcionales”.  Cada uno de ellos contará con las definiciones técnicas y funcionales necesarias.

## Tecnologías y herramientas utilizadas 🔧
Tecnologías | Uso
------------------- | ----------------------
 **Git + Github**  | Para el manejo de versiones, utilizando diferentes ramas trabajando en equipo y realizando pull requests con cada funcionalidad terminada.
 **Java**  | Lenguaje utilizado para el desarrollo de la aplicación REST, permitiéndonos utilizar sus librerías que hacen posible el proyecto.
 **Spring Boot** | herramineta utilizada para levantar la REST API, creando endpoints a través de un controlador.
 **JPA + Hibernate** | Conexión entre los servicios desarrollados en el proyecto y la base de datos. Creando entidades que generan las tablas de base de datos y las relaciones automáticamente.
 **Testing** | Generamos testing para tener un código de calidad.
 **Trello** | Para la organización del proyecto en el equipo.
 **Workbench** | Herramienta para manejo y control de la base de datos
 **Postman** | Herramienta para genera colecciones de request a modo de prueba de nuestra aplicación.
 **IntelliJ** | IDE, herramienta para la generación del proyecto JAVA

## Instalación ⚙️
Para correr la aplicación se tiene que clonar el repo en la carpeta deseada con el comando ``` git clone https://github.com/toshi-uy/DesafioFinalGrupo3.git```
Abrir el proyecto en el IntelliJ y correr la aplicación (comando Shift+F10). Luego puedes utilizar Postman para crear hoteles, vuelos, reservas y paquetes, y verificar todo lo creado. Puedes utilizar la colección de Postman que tenemos en el source del proyecto [DesafioFinal.postman_collection.json](https://github.com/toshi-uy/DesafioFinalGrupo3/blob/Requerimiento1/DesafioFinal.postman_collection.json "DesafioFinal.postman_collection.json").


## Requerimiento Nº 1 
### Especificaciones técnicas y funcionales
Implementación de una base de datos relacional
En las versiones anteriores de la aplicación, el manejo de datos se llevó a cabo con una “base de datos” lógica, implementada mediante collections o maps y en algunos casos JSON. Llegó el momento de implementar una base de datos relacional que permita realizar operaciones CRUD/ABML.

#### User Story
<img src="https://i.imgur.com/Nrktyg8.png">

### Especificaciones Técnicas necesarias
#### End-Points
<img src="https://i.imgur.com/iOzWBLH.png">

## Requerimiento Nº 2

### Especificaciones técnicas y funcionales

#### Paquetes Turísticos

La agencia de viajes y turismo desea empezar a implementar el armado de paquetes turísticos; para esto, especificó que cada paquete turístico podrá estar conformado de la siguiente manera:

-   Dos reservas de vuelos
-   Dos reservas de hotel
-   Una reserva de vuelo y una reserva de hotel

Los paquetes turísticos ofrecen como beneficio al cliente, un descuento del 10% sobre el valor total de la sumatoria de los dos ítems que tenga incorporado. Por ejemplo: Si se tiene una reserva de vuelo por $30.000 y una reserva de hotel por $15.000, la sumatoria total es de $45.000. Si aplicamos el descuento del 10% tendremos $45.000 - $4500, por lo que el precio total del paquete sería de $40.500.

Cabe destacar que antes de armar el paquete turístico ya deben estar ambas reservas registradas y el descuento se aplica sobre la sumatoria del total de ambas.

### User Story
<img src="https://i.imgur.com/o9UdBDc.png">

### Especificaciones Técnicas necesarias

### End-Points
<img src="https://i.imgur.com/YIYmOrG.png">

## Requerimiento Nº 3

### Especificaciones técnicas y funcionales

#### Implementación de sistema de caja

El dueño de la agencia de turismo manifestó la necesidad de conocer los montos brutos totales que ingresan en un determinado día o mes a partir de cada una de las reservas realizadas. Para ello solicita que sus empleados sean capaces de realizar consultas por día o mes y brindarle dicha información.

### User Story
<img src="https://i.imgur.com/MjHCaDS.png">

### Especificaciones Técnicas necesarias
### End-Points

<img src="https://i.imgur.com/k3kouMw.png">

## Equipo 👥
- [**Bruno Silva**](https://github.com/brux86)
- [**Lucia Alvariño**](https://github.com/luualvarino)
- [**Oscar Echeverria**](https://github.com/Oeecheverria)
- [**Santiago Borgia**](https://github.com/toshi-uy)

## License 📑
Public Domain. No copy write protection. 
