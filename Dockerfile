FROM adoptopenjdk/openjdk13
EXPOSE 9000
ADD target/homeautomation.war homeautomation.war
ENTRYPOINT ["java", "-jar", "/homeautomation.war"]
