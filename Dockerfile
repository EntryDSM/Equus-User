FROM openjdk:17
ENV TZ=Asia/Seoul
COPY build/libs/Equus-User-0.0.0.jar app.jar

ARG CLOUD_CONFIG_IMPORT_URL
ENV CLOUD_CONFIG_IMPORT_URL=$CLOUD_CONFIG_IMPORT_URL

ENTRYPOINT ["java", "-jar", "app.jar"]
