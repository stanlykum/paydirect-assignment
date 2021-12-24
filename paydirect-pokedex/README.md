# paydirect-pokedox

Paydirect Pokedox Service

## Purpose

This SpringBoot application covers the implementation of the Paydirect Pokedox Service, which manages below task.

1) It invoke pokemon API to get basic Pokemon information
2) Fun Description is generated with either yodo or shakespeare translation type by invoking FunTranslator API.

## Technologies

| Name       | Used              |
|------------|-------------------|
| Build Tool | Gradle            |
| Language   | Java 11           |
| Framework  | Spring boot       |
| Testing    | Junit,Mockito,Spring Test  |

## Contents

Outline the file contents of the repository. It helps users navigate the codebase, build configuration and any related
assets.

| File/folder       | Description                                |
|-------------------|--------------------------------------------|
| `src`             | Source code.                        |
| `.gitignore`      | Define what to ignore at commit time.      |
| `build.gradle`    | The gradle configuration to this project.  |
| `README.md`       | This README file.                          |

### Prerequisites

To run the project you will need to have the following installed:

* Java 11
* Docker (optional)

For information about the software versions used to build this API and a complete list of it's dependencies see
build.gradle

## Building the application

The project uses [Gradle](https://gradle.org) as a build tool. It already contains
`./gradlew` wrapper script, so there's no need to install gradle.

To build the project execute the following command:

```bash
  ./gradlew build
```

To clean up your environment use the following, it will delete any temporarily generated files such as reports.

```bash
  ./gradlew clean
```

### Running

Alternatively, you can start the application from the current source files using Gradle as follows:

 ```
 ./gradlew clean bootRun
 ```

## Endpoints

[httpie](https://httpie.io/) is an open-source API testing client for open minds.

- Used to call Basic Pokemon information

```
GET http://localhost:8080/pokemon/mewtwo
httpie http://localhost:8080/pokemon/mewtwo
```

- Used to call Pokemon with fun description information

```
GET http://localhost:8080/pokemon/translated/mewtwo
httpie http://localhost:8080/pokemon/translated/mewtwo
```

## Swagger

API contract can be found on below link

```
http://localhost:8080/swagger-ui.html
```

## Docker

Latest Docker build available at stanlyajeesh/stanly-docker:paydirect-pokedex

Docker Build

```
docker build -t stanlyajeesh/stanly-docker:paydirect-pokedex .
```

Docker Run

```
docker run -p 8080:8080 stanlyajeesh/stanly-docker:paydirect-pokedex
```