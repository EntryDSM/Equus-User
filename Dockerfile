FROM openjdk:17
ENV TZ=Asia/Seoul
COPY build/libs/Equus-User-0.0.0.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
