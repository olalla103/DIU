FROM openjdk:23


COPY target/Tutorials-0.0.1-SNAPSHOT.jar /tutorialsapp2.jar
CMD ["java", "-jar", "/tutorialsapp2.jar"]