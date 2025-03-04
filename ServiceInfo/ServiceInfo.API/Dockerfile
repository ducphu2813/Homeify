# stage 1: dùng image maven để build project
FROM maven:3.9.9-amazoncorretto-21-alpine AS builder

WORKDIR /app

# expose port
EXPOSE 8080

# stage 2: build code
FROM maven:3.9.9-amazoncorretto-21-alpine AS builder
WORKDIR /app

# copy pom.xml và tải dependencies (để có thể xài cache hiệu quả)
COPY ServiceInfo/ServiceInfo.Entities/pom.xml ServiceInfo/ServiceInfo.Entities/pom.xml
COPY ServiceInfo/ServiceInfo.UseCases/pom.xml ServiceInfo/ServiceInfo.UseCases/pom.xml
COPY ServiceInfo/ServiceInfo.DB/pom.xml ServiceInfo/ServiceInfo.DB/pom.xml
COPY ServiceInfo/ServiceInfo.API/pom.xml ServiceInfo/ServiceInfo.API/pom.xml

# build từng module phụ riêng lẻ
COPY ServiceInfo/ServiceInfo.Entities/ ServiceInfo/ServiceInfo.Entities/
RUN mvn -f ServiceInfo/ServiceInfo.Entities/pom.xml  install -DskipTests -Dmaven.repo.local=/root/.m2

COPY ServiceInfo/ServiceInfo.UseCases/ ServiceInfo/ServiceInfo.UseCases/
RUN mvn -f ServiceInfo/ServiceInfo.UseCases/pom.xml  install -DskipTests -Dmaven.repo.local=/root/.m2

COPY ServiceInfo/ServiceInfo.DB/ ServiceInfo/ServiceInfo.DB/
RUN mvn -f ServiceInfo/ServiceInfo.DB/pom.xml  install -DskipTests -Dmaven.repo.local=/root/.m2

#copy phần code của module API
COPY ServiceInfo/ServiceInfo.API/ ServiceInfo/ServiceInfo.API/
RUN mvn -f ServiceInfo/ServiceInfo.API/pom.xml  package -DskipTests -Dmaven.repo.local=/root/.m2

#stage 3: run app
FROM amazoncorretto:21-alpine AS runtime

WORKDIR /app

COPY --from=builder /app/ServiceInfo/ServiceInfo.API/target/ServiceInfoAPI-0.0.1-SNAPSHOT.jar /app/serviceinfo.jar

ENTRYPOINT ["java", "-jar", "/app/serviceinfo.jar"]