FROM openjdk:11-jre-slim

ENV LANG C.UTF-8


ADD build/libs/bookstore-backend.jar bookstore-backend.jar


ENTRYPOINT ["java", "-jar", "bookstore-backend.jar"]

EXPOSE 8080

#docker build . --tag=heroes-api    run command in the project root . means here and --tag is a name for docker image think postgres:12, postgres:11
#docker run heroes-api    you can add -d for detached
#docker ps show all containers
#docker inspect <container id> look for
#you can try starting container with --network=host, this is platform dependant
