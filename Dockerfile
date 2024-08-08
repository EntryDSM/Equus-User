FROM openjdk:17
ENV TZ=Asia/Seoul

COPY build/libs/Equus-User-0.0.0.jar app.jar
COPY /datadog/dd-java-agent.jar /usr/agent/dd-java-agent.jar

ARG CLOUD_CONFIG_IMPORT_URL
ENV CLOUD_CONFIG_IMPORT_URL=$CLOUD_CONFIG_IMPORT_URL

ARG USER_PROFILE
ENV USER_PROFILE=$USER_PROFILE

ENTRYPOINT ["java","-javaagent:/usr/agent/dd-java-agent.jar", "-Ddd.agent.host=localhost", "-Ddd.profiling.enabled=true","-XX:FlightRecorderOptions=stackdepth=256", "-Ddd.logs.injection=true", "-Ddd.service=user-api", "-Ddd.env=prod", "-Dspring.profiles.active=production", "-jar", "/app.jar"]
