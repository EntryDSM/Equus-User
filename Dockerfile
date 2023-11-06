FROM openjdk:17
ENV TZ=Asia/Seoul
COPY ./build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"] 
