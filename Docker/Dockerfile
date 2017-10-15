FROM openjdk:8-jdk
COPY maven /maven/
CMD java -Duser.timezone=Europe/Berlin -Dbasedir.static.pages=/maven -jar /maven/hsm-arduino-backend-webapp.jar
