#!/bin/sh
echo "Aguardando o MySQL iniciar..."
while ! nc -z mysql 3306; do
  sleep 1
done
echo "MySQL está pronto! Iniciando aplicação..."
exec java -jar /app/app.jar
