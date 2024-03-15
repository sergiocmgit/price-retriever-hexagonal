# price-retriever-hexagonal

The purpose of this project is to build a demo service
using **domain-driven design** and **hexagonal architecture**.
The topic turns around prices linked to timeframes and the query of them.

## Prerequisites

- Java 17
- Gradle 8.5
- Docker

## Installation

1. Clone the repository
   ```sh
   git clone git@github.com:sergiocmgit/price-retriever-hexagonal.git
   ```
2. Build the image
   ```sh
   gradle build
   ```
3. Build the docker image
   ```sh
   docker build -t price-retriever-hexagonal .
   ```
4. Run the application
   ```sh
   docker run -p 8080:8080 price-retriever-hexagonal
   ```
5. Call the endpoint
    ```sh
   curl -X GET "http://localhost:8080/api/prices?productId=35455&brandId=1&appliedAt=2020-06-14T10%3A00%3A00Z"
   ```

## Documentation

Check the documentation when the application is running by accessing the following:

- Swagger documentation: [Swagger UI](http://localhost:8080/swagger-ui/index.html#/)
- OpenAPI documentation in json format: [OpenAPI in JSON](http://localhost:8080/v3/api-docs)
- OpenAPI documentation in yaml format: [OpenAPI in YAML](http://localhost:8080/v3/api-docs.yaml)
