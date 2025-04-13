Python 3.10:
1. Build 
docker build -t ubuntu-python:3.10 .
2. Run
docker run --rm ubuntu-python:3.10

Kotlin app:
1. Build 
docker build -t kotlin-gradle-app .
2. Run
docker run --rm kotlin-gradle-app gradle run

Docker compose
1. Build
docker-compose build
2. Run
docker-compose up