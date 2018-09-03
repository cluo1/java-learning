FROM openjdk:8
VOLUME /opt/tmp
ADD nearjob.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]
EXPOSE 8080