# Reto BACK-END
Programacion Back-End Java Con Spring Boot

# Implementacion y configuracion de Base
Se requiere Crear una base de datos en Postgres con el nombre: inventario

Se requiere configurar las propiedades de la aplicacion en la siguiente ruta:

src/main/resources/application.properties

spring.datasource.url=jdbc:postgresql://localhost:<port>/<name>
spring.datasource.username=<user>
spring.datasource.password=<password>
spring.jpa.show-sql=true

# Configuracion Puerto
Opcional configurar las propiedades de la aplicacion en la siguiente ruta:

server.port=8080

Nota: Puerto por defecto del tomcat es 8080

# Open Api Swagger
puerto configurado

- http://localhost:<port>/swagger-ui/index.html