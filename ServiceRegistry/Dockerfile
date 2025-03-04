# stage 1: build
FROM maven:3.9.9-amazoncorretto-21-alpine AS build

WORKDIR /app

# copy pom.xml và tải dependencies (để có thể xài cache hiệu quả)
COPY pom.xml .
RUN mvn dependency:go-offline -Dmaven.repo.local=/root/.m2 -Dmaven.wagon.http.retryHandler.count=3

# copy toàn bộ project sau đó clean và package
COPY src ./src
RUN mvn clean package -DskipTests -Dmaven.repo.local=/root/.m2

# stage 2: Run app
FROM amazoncorretto:21-alpine AS runtime

WORKDIR /app

# copy file jar đã được build trước đó vào container
COPY --from=build /app/target/ServiceRegistry-0.0.1-SNAPSHOT.jar /app/service-registry.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/service-registry.jar"]