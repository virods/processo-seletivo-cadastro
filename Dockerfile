FROM maven:3.8.4-openjdk-17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package

FROM openjdk:17
COPY --from=builder /app/target/processo-seletivo-cadastro-0.0.1-SNAPSHOT.jar /usr/src/processo-seletivo-cadastro-0.0.1-SNAPSHOT.jar
WORKDIR /usr/src
ENTRYPOINT ["java", "-jar", "processo-seletivo-cadastro-0.0.1-SNAPSHOT.jar"]

