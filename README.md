# simple-aspect-example [![Version](https://img.shields.io/badge/Version-0.1-color.svg)](https://github.com/igabaydulin/simple-aspect-example) [![Version](https://img.shields.io/badge/Java-OpenJDK%2011.0.1-dd0000.svg?logo=java)](https://jdk.java.net/11/) [![Version](https://img.shields.io/badge/Gradle-5.2.1-1ba8cb.svg)](https://docs.gradle.org/5.2.1/release-notes.html) [![Version](https://img.shields.io/badge/Spring%20Boot-2.1.3.RELEASE-color.svg)](https://github.com/spring-projects/spring-boot/releases/tag/v2.1.3.RELEASE)
Implementation example for aspects in Spring; also shows difference beetween Spring's aspects and Spring's interceptors (based on Stackoverflow's [question](https://stackoverflow.com/questions/28975025/advise-controller-method-before-valid-annotation-is-handled))
## Gradle
* Application execution by Spring Boot Gradle plugin:
    ```
    ./gradlew clean bootRun
    ```
* Application execution by Jar:
    ```
    ./gradlew clean bootJar
    java -jar ./build/libs/simple-aspect-example-0.1.jar
    ```

## Request
* Valid request
    ```
    curl localhost:8080/ping?username=Igor
    ```
    Response:
    ```
    {
      "message": "Hello, Igor"
    }
    ```
    Log output:
    ```
    log interceptor is executed
    Request param username: [Igor]
    log aspect is executed
    Request param username: [Igor]
    ```

* Invalid request
    ```
    curl localhost:8080/ping
    ```
    Response:
    ```
    {
      "timestamp":"2019-03-13T15:49:26.788+0000",
      "status":400,
      "error":"Bad Request",
      "message":"Required String parameter 'username' is not present",
      "path":"/ping"
    }
    ```
    Log output:
    ```
    log interceptor is executed
    ```

