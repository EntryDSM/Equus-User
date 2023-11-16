FROM openjdk:17
ENV TZ=Asia/Seoul

COPY build/libs/Equus-User-0.0.0.jar app.jar
COPY rolls/license/V61290000000_IDS_01_PROD_AES_license.dat /rolls/license/V61290000000_IDS_01_PROD_AES_license.dat
COPY /home/ec2-user/dd-java-agent.jar /usr/agent/dd-java-agent.jar

ARG CLOUD_CONFIG_IMPORT_URL
ENV CLOUD_CONFIG_IMPORT_URL=$CLOUD_CONFIG_IMPORT_URL

ENTRYPOINT ["java","-javaagent:/usr/agent/dd-java-agent.jar", "-Ddd.agent.host=localhost", "-Ddd.profiling.enabled=true","-XX:FlightRecorderOptions=stackdepth=256", "-Ddd.logs.injection=true", "-Ddd.service=discovery-api", "-Ddd.env=prod", "-Dspring.profiles.active=production", "-jar", "/app.jar"]
