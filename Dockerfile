FROM openjdk:17
ADD ./target/processo-seletivo-cadastro-0.0.1-SNAPSHOT.jar /usr/src/processo-seletivo-cadastro-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java", "-jar", "processo-seletivo-cadastro-0.0.1-SNAPSHOT.jar"]
