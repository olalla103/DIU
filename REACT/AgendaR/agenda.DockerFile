FROM openjdk:23

COPY target/AgendaR-0.0.1-SNAPSHOT.jar /Agendasapp2.jar
CMD ["java", "-jar", "/Agendasapp2.jar"]