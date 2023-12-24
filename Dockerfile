FROM openjdk:17-oracle

EXPOSE 5500

ADD target/courseProjectMoneyTransferService-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]