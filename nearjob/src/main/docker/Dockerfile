FROM openjdk:8
VOLUME /opt/tmp
ADD nearjob.jar nearjob.jar

ENTRYPOINT ["java","-jar","-Xms64m","-Xmx64m","-Dnearjob.host=localhost","-Dspring.redis.port=12379","nearjob.jar"]
EXPOSE 10000