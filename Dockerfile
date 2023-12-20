# Usa una imagen base de OpenJDK para Java 11
FROM openjdk:17

  # Establece el directorio de trabajo en /app
WORKDIR /app

  # Copia el archivo JAR construido por Spring Boot en el directorio de trabajo
COPY target/MovementsApp-0.0.1-SNAPSHOT.jar app.jar

  # Expone el puerto 8085
EXPOSE 8085

  # Comando para ejecutar la aplicación Spring Boot cuando se inicie el contenedor
CMD ["java", "-jar", "app.jar"]