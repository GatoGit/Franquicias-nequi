# Franquicias API

API REST para manejar franquicias, sucursales y productos con Spring Boot y MongoDB.

Tecnologías usadas:  
  - Java 17  
  - Spring Boot  
  - MongoDB  
  - Docker y Docker Compose  
  - Maven  

Requisitos:  
  - Java 17 (solo si ejecutas sin Docker)  
  - Maven (solo si compilas manualmente)  
  - Docker y Docker Compose instalados (opcional, recomendado)  

Configuración de MongoDB:  
  - Si usas Docker Compose, MongoDB se levanta automáticamente como contenedor  
  - Si no, debes tener MongoDB instalado y corriendo localmente en el puerto 27017  

Configuración del proyecto (`src/main/resources/application.properties`):  
  spring.data.mongodb.uri=mongodb://mongodb:27017/franquicias  
  server.port=8080  
  (Cambiar `mongodb` por `localhost` si usas Mongo local)  

Cómo compilar y ejecutar sin Docker:  
  1. `mvn clean package`  
  2. `java -jar target/franquicias-api-1.0.0.jar`  
  3. Accede en `http://localhost:8080`  

Cómo ejecutar con Docker y Docker Compose:  
  1. `docker-compose up --build`  
  2. Accede en `http://localhost:8080`  
  3. MongoDB corre en contenedor accesible desde la API  

Archivos relevantes:  
  - `Dockerfile`  
  - `docker-compose.yml`  
  - `application.properties`  
  - Código fuente en `src/main/java/com/nequi/franquicias`  

Comandos útiles:  
  - `docker ps`  
  - `docker logs -f franquicias-api`  
  - `docker-compose down`  

Endpoints disponibles:  
  - POST `/franquicias` (crear franquicia)  
  - GET `/franquicias` (listar franquicias)  
  - PUT `/franquicias/{id}` (actualizar nombre franquicia)  
  - POST `/franquicias/{id}/sucursales` (agregar sucursal)  
  - PUT `/franquicias/{id}/sucursales/{idSucursal}` (actualizar sucursal)  
  - POST `/franquicias/{id}/sucursales/{idSucursal}/productos` (agregar producto)  
  - DELETE `/franquicias/{id}/sucursales/{idSucursal}/productos/{idProducto}` (eliminar producto)  
  - PUT `/franquicias/{id}/sucursales/{idSucursal}/productos/{idProducto}/stock` (modificar stock)  
  - GET `/franquicias/{id}/productos/mayor-stock` (producto mayor stock por sucursal)  

Desarrollado:  
  Santiago Soto Vallejo
  sant4562@gmail.com
  3162230515
