# Dockerfile (na raiz do projeto)

# 1) Base: Java 17 (OpenJDK em Alpine, imagem leve)
FROM eclipse-temurin:17-jdk-alpine

# 2) Define /app como diretório de trabalho dentro do container
WORKDIR /app

# 3) Copia o JAR que o Maven gerou (em target/) para dentro do container como "app.jar"
COPY target/backend-0.0.1-SNAPSHOT.jar app.jar

# 4) Copia o script que aguarda o MySQL
COPY wait-for-mysql.sh wait-for-mysql.sh

# 5) Dá permissão de execução ao script
RUN chmod +x wait-for-mysql.sh

# 6) Expõe a porta em que o Spring Boot ouvirá (conforme application.properties)
EXPOSE 8080

# 7) Ao iniciar o container, executa o script que aguarda o MySQL e só então roda o JAR
ENTRYPOINT ["./wait-for-mysql.sh"]
