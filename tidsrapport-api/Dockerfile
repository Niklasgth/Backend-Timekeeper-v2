# Bygg fas
FROM eclipse-temurin:21-jdk-jammy AS builder
WORKDIR /opt/app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

COPY ./src ./src
RUN ./mvnw clean package -DskipTests

# Kör fas
FROM eclipse-temurin:21-jre-jammy
WORKDIR /opt/app
EXPOSE 8080

# Kopiera färdigt jar
COPY --from=builder /opt/app/target/*.jar /opt/app/app.jar

# Kör Spring Boot i default (dev)-läge
ENTRYPOINT ["java", "-jar", "/opt/app/app.jar"]
