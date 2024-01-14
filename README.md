## Spring Boot

Proyecto Spring Boot con las dependencias / Starters:
* H2
* Spring Data JPA
* Spring Web
* Spring Boot Dev Tools

Aplicación API Rest con acceso a base de datos H2 para persistir la información.

El acceso se podrá realizar desde postman o navegador.

Una biblioteca que tenga libros y que nos permita almacenar el catálogo de los libros en base de datos.

Orden de construcción:
1.Book              Entidad a persistir
2.Book Repository   Repositorio, interfaz

4.Book Controller
    1. Buscar todos los libros
    2. Buscar un libro solamente
    3. Crear un nuevo libro
    4. Actualizar un libro existente
    5. Borrar un libro
    6. Borrar todos los libros