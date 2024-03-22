FROM maven:3.8.4-openjdk-17
EXPOSE 8080
EXPOSE 3306
COPY target/neuconnect-0.0.1-SNAPSHOT.jar neuconnect-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/neuconnect-0.0.1-SNAPSHOT.jar"]