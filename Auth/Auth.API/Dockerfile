# stage 1: dùng image maven để build project
FROM maven:3.9.9-amazoncorretto-21-alpine AS builder

WORKDIR /app

# expose port
EXPOSE 8080

# stage 2: build code
FROM maven:3.9.9-amazoncorretto-21-alpine AS builder
WORKDIR /app

# copy pom.xml và tải dependencies (để có thể xài cache hiệu quả)
COPY Auth/Auth.Entities/pom.xml Auth/Auth.Entities/pom.xml
COPY Auth/Auth.UseCases/pom.xml Auth/Auth.UseCases/pom.xml
COPY Auth/Auth.DB/pom.xml Auth/Auth.DB/pom.xml
COPY Auth/Auth.API/pom.xml Auth/Auth.API/pom.xml

# build từng module phụ riêng lẻ
COPY Auth/Auth.Entities/ Auth/Auth.Entities/
RUN mvn -f Auth/Auth.Entities/pom.xml install -DskipTests -Dmaven.repo.local=/root/.m2

COPY Auth/Auth.UseCases/ Auth/Auth.UseCases/
RUN mvn -f Auth/Auth.UseCases/pom.xml install -DskipTests -Dmaven.repo.local=/root/.m2

COPY Auth/Auth.DB/ Auth/Auth.DB/
RUN mvn -f Auth/Auth.DB/pom.xml install -DskipTests -Dmaven.repo.local=/root/.m2

# copy toàn bộ mã nguồn của dự án và build module API
COPY Auth/Auth.API/ Auth/Auth.API/
RUN mvn -f Auth/Auth.API/pom.xml package -DskipTests -Dmaven.repo.local=/root/.m2

# stage 3: run app
FROM amazoncorretto:21-alpine AS runtime

WORKDIR /app

COPY --from=builder /app/Auth/Auth.API/target/AuthAPI-0.0.1-SNAPSHOT.jar /app/auth.jar

ENTRYPOINT ["java", "-jar", "/app/auth.jar"]
