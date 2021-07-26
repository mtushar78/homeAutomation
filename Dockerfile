FROM openjdk:8
EXPOSE 8081
ADD target/homeautomation.war homeautomation.war
ENTRYPOINT ["java", "-jar", "/homeautomation.war"]
