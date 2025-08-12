
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/*.jar /app/springing.jar
ENTRYPOINT ["java","-Dspring.profiles.active=dev","-jar","/app/springing.jar"]

# build the image with
# sudo docker build -t greptips/springing:latest .
