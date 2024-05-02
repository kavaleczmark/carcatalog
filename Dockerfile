FROM openjdk:17-jdk-alpine3.14
COPY "./target/carcatalog.jar" "/app/carcatalog.jar"
EXPOSE 8080
CMD [ "java", "-jar", "/app/carcatalog.jar" ]