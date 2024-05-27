# Code to use wrapper gradle to make the build without having gradle installed
#FROM gradle:latest AS builder
#WORKDIR /app
#COPY build.gradle .
#COPY settings.gradle .
#COPY gradle ./gradle
#COPY . .
#RUN gradle build --no-daemon
#RUN ls -la build/libs

FROM openjdk:17-jdk-slim
WORKDIR /app
#COPY --from=builder build/libs/*.jar /app/springing.jar
COPY build/libs/*.jar /app/springing.jar
EXPOSE 8888
ENTRYPOINT ["java","-jar","/app/springing.jar"]
