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
**or**

```bash
  gradlew clean
```

### Running

Alternatively, you can start the application from the current source files using Gradle as follows:

 ```
 ./gradlew clean bootRun
 ```
 **or**
  ```
 gradlew clean bootRun
 ```
 
 ### Running

Testsuites can be run with below commands

 ```
 ./gradlew clean bootRun
 ```
 **or**
 ```
 gradlew clean test
 ```

## Endpoints

[httpie](https://httpie.io/) is an open-source API testing client for open minds.

- Used to call Basic Pokemon information

```
GET http://localhost:8080/pokemon/mewtwo
httpie http://localhost:8080/pokemon/mewtwo
```
![1](https://user-images.githubusercontent.com/30554963/147368071-3bcc36f1-11e8-49f2-beac-cdf5725e6c2f.JPG)


- Used to call Pokemon with fun description information

```
GET http://localhost:8080/pokemon/translated/mewtwo
httpie http://localhost:8080/pokemon/translated/mewtwo
```
![2](https://user-images.githubusercontent.com/30554963/147368077-ee4f3efd-e07e-4cf1-b6ec-5f8892a7ef1f.JPG)


## Swagger

API contract can be found on below link

```
http://localhost:8080/swagger-ui.html
```
![5](https://user-images.githubusercontent.com/30554963/147368045-741fc0f1-31fd-4265-bc61-e366c1c6ba0d.JPG)

![6](https://user-images.githubusercontent.com/30554963/147368048-124ea640-3378-4588-8947-cb64fa6dddc6.JPG)

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

Docker Pull

```
docker pull stanlyajeesh/stanly-docker:paydirect-pokedex
```
