FROM openjdk:16
COPY . /usr/src/yurt
WORKDIR /usr/src/yurt
CMD ["./mvn", "clean", "package"]
CMD ["java", "-jar", "target/yurt-0.0.1-SNAPSHOT.jar"]
