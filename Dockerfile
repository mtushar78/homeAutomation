FROM adoptopenjdk/openjdk13
EXPOSE 9000
ADD war/homeautomation.war homeautomation.war
ENTRYPOINT ["java", "-jar", "/homeautomation.war"]
