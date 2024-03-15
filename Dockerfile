FROM eclipse-temurin:17
COPY ./build/libs/price-retriever-hexagonal-0.0.1.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]