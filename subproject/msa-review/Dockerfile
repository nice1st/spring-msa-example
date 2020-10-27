FROM openjdk:14-jdk
VOLUME /tmp
COPY build/libs/msa-review-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar", "/app.jar"]