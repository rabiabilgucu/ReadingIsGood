FROM openjdk:11
ADD target/ReadingIsGood-0.0.1-SNAPSHOT.jar ReadingIsGood-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","ReadingIsGood-0.0.1-SNAPSHOT.jar"]
