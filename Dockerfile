FROM maven:3.8.4-openjdk-17
WORKDIR /opt
ENV PORT 8080
EXPOSE 8080
EXPOSE 3306
# Copy the JAR file into the container
COPY target/*.war /opt/app.war

# Define entrypoint to execute the JAR file
ENTRYPOINT exec java $JAVA_OPTS -jar app.war
