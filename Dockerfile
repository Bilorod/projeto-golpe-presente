FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY target/backend-0.0.1-SNAPSHOT.jar app.jar
COPY wait-for-mysql.sh .

RUN chmod +x wait-for-mysql.sh

ENTRYPOINT ["./wait-for-mysql.sh"]
