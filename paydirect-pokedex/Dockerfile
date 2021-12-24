# define base docker image
FROM openjdk:11
LABEL maintainer="stanlyajeesh"
ADD build/libs/paydirect-pokedex-0.0.1-SNAPSHOT.jar paydirect-pokedex.jar
ENTRYPOINT ["java", "-jar", "paydirect-pokedex.jar"]