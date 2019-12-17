FROM openjdk:8-jdk-alpine
RUN mkdir /app
COPY target/*.jar /app/mensagem.jar
WORKDIR /app
EXPOSE 8072
ENTRYPOINT ["java", "-jar", "mensagem.jar"]