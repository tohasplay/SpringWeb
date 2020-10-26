FROM store/oracle/jdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
VOLUME /resources
ENTRYPOINT ["java","-jar","/app.jar"]