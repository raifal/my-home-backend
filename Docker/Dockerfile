FROM amazoncorretto:11.0.3

RUN mkdir -p /app
COPY target/my-home-backend-webapp.jar /app
CMD java -Duser.timezone=Europe/Berlin -jar /app/my-home-backend-webapp.jar
