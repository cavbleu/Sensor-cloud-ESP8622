#https://docs.docker.com/engine/reference/builder/
FROM openjdk:17
COPY build/libs/base-sensors-1.0.1-plain.jar base-sensor.jar
ENTRYPOINT ["java","-jar","/base-sensors.jar"]
EXPOSE 8189
