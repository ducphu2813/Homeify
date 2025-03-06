# stage 1: dùng image maven để build project
FROM maven:3.9.9-amazoncorretto-21-alpine AS builder

WORKDIR /app

# expose port
EXPOSE 8080

# stage 2: build code
FROM maven:3.9.9-amazoncorretto-21-alpine AS builder
WORKDIR /app

# copy pom.xml và tải dependencies (để có thể xài cache hiệu quả)
COPY Booking/Booking.Entities/pom.xml Booking/Booking.Entities/pom.xml
COPY Booking/Booking.UseCases/pom.xml Booking/Booking.UseCases/pom.xml
COPY Booking/Booking.DB/pom.xml Booking/Booking.DB/pom.xml
COPY Booking/Booking.API/pom.xml Booking/Booking.API/pom.xml

# build từng module phụ riêng lẻ
COPY Booking/Booking.Entities/ Booking/Booking.Entities/
RUN mvn -f Booking/Booking.Entities/pom.xml install -DskipTests -Dmaven.repo.local=/root/.m2

COPY Booking/Booking.UseCases/ Booking/Booking.UseCases/
RUN mvn -f Booking/Booking.UseCases/pom.xml install -DskipTests -Dmaven.repo.local=/root/.m2

COPY Booking/Booking.DB/ Booking/Booking.DB/
RUN mvn -f Booking/Booking.DB/pom.xml install -DskipTests -Dmaven.repo.local=/root/.m2

# copy toàn bộ mã nguồn của dự án và build module API
COPY Booking/Booking.API/ Booking/Booking.API/
RUN mvn -f Booking/Booking.API/pom.xml  package -DskipTests -Dmaven.repo.local=/root/.m2


# stage 3: run app
FROM amazoncorretto:21-alpine AS runtime

WORKDIR /app

COPY --from=builder /app/Booking/Booking.API/target/BookingAPI-0.0.1-SNAPSHOT.jar /app/booking.jar

ENTRYPOINT ["java", "-jar", "/app/booking.jar"]