FROM maven:3.9.6-eclipse-temurin-21-jammy AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests -B

FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
COPY --from=build /app/target/bank-hellen-app.jar app.jar
RUN mkdir -p data
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]