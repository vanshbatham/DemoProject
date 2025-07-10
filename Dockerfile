# Use official Tomcat image with JDK 21
FROM tomcat:10.1-jdk21-temurin

# Clear default webapps (ROOT, docs, etc.)
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy your WAR file and rename it to ROOT.war so it runs at /
COPY Signup.war /usr/local/tomcat/webapps/ROOT.war

# Expose default Tomcat port
EXPOSE 8080

# Start Tomcat when the container runs
CMD ["catalina.sh", "run"]
