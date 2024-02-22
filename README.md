# spring-db-storke

### How to run the application in local?

To run the application in local, you need to have the following installed in your system:
`java 11`, `docker`, `docker-compose`

Once you have the above installed, you can run the following command to start the application:

1. Start the local database
    ```bash
    docker-compose -f docker-compose-test.yaml up -d
    ```
2. Build & Run the application
    ```bash
    ./gradlew build
    java -jar build/libs/spring-db-storke-0.0.1-SNAPSHOT.jar
    ```