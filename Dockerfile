
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/*.jar /app/springing.jar
EXPOSE 8181
ENTRYPOINT ["java","-jar","/app/springing.jar"]

# build the image with
# sudo docker build -t greptips/springing:latest .
