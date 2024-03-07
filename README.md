# Devsu Test

Esta es la solución diseñada para satisfacer los requisitos establecidos en la prueba.

## Requerimientos

Asegúrate de tener los siguientes requisitos antes de instalar y utilizar la solución:

- Sistema operativo Linux (puede ser Ubuntu 22.04 u otra versión compatible)
- [Amazon Corretto OpenJDK 17](https://aws.amazon.com/es/corretto/) (u otra versión compatible)
- [PostgreSQL 15](https://www.postgresql.org/)
- [Docker](https://www.docker.com/)
- [Gradle 8](https://gradle.org/)
- [Java IDE](https://www.jetbrains.com/idea/) (por ejemplo, IntelliJ IDEA) o compatible
- [Postman](https://www.postman.com/) (por ejemplo, IntelliJ IDEA) o compatible

## Uso

Para utilizar la solución, sigue estos pasos:

1. **Descarga el repositorio cliente:** [Enlace al Repositorio Cliente](https://github.com/janeth-roldan/cliente).
2. **Descarga el repositorio cuenta:** [Enlace al Repositorio Cuenta](https://github.com/janeth-roldan/cuenta).
3. Abre cada uno de los proyectos en tu Java IDE (por ejemplo, IntelliJ IDEA).
4. Ejecuta el script de base de datos BaseDatos.sql desde una consola psql.
5. Importa la colección de Postman.

### Utilizando el IDE

1. **Edita los archivos `application.yml`:** En ambos proyectos (`repositorio-cliente` y `repositorio-cuenta`),  y coloca la dirección IP de tu servidor PostgreSQL.

Ejemplo de archivo `application.yml`:

```yaml
# Configuración para conexión a PostgreSQL
spring:
  datasource:
    driver-class-name: org.postgresql.Driver
    url: jdbc:postgresql://<POSTGRES_HOST>:5432/cliente
```
Los microservicios utilizan las bases de datos denominadas "cliente" y "cuenta", respectivamente.

2. Ejecuta la aplicación.

### Utilizando contenedores

1. Dentro del directorio `repositorio-cliente`, ejecuta la tarea Gradle `bootJar`.
```bash
   $ chmod +x gradlew
   $ ./gradlew bootJar
```
2. Copia el archivo JAR generado, en este caso, por ejemplo, cliente-0.0.1-SNAPSHOT.jar, al siguiente directorio mediante el siguiente comando: 
```bash
   $ cp ./build/libs/<NOMBRE_JAR> ./docker/build/exec
```
3. Copia el archivo application.yml previamente editado, al siguiente directorio mediante el siguiente comando:
```bash
   $ cp ./src/main/resources/application.yml ./docker/build/exec
```
4. Concede permisos de ejecución al directorio de Docker
```bash
   $ chmod +x -R ./docker
```
5. Para construir el contenedor, emplea el siguiente comando: 
```bash
   $ ./docker/build/build.sh
```
6. Para ejecutar el contenedor, utiliza el siguiente comando:
```bash
   $ ./docker/exec/run.sh
```
7. Para acceder a la interfaz de línea de comandos del contenedor, utiliza el siguiente comando:
```bash
   $ ./docker/exec/attach.sh
```
8. Repite los pasos anteriores para el directorio `repositorio-cuenta`.

**NOTA:** Los contenedores de cliente y cuenta están configurados para ejecutarse en los puertos 8080 y 8081, respectivamente.
