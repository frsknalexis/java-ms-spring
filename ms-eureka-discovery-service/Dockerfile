#pull latest gradle images as builder
FROM gradle:latest as builder
#copy all codes to /app/gradle/src and chown: change ownership of files and directories
ADD --chown=gradle:gradle . /app/gradle/src
#change working directory: cd /app/gradle/src
WORKDIR /app/gradle/src
#create jar file for user-service
RUN gradle clean assemble

#pull java 8 images
FROM java:8
#permanent storage folder
VOLUME /tmp
COPY ./wait-for-it.sh ./wait-for-it.sh
#wait-for-it.sh will be executable script.
RUN chmod +x /wait-for-it.sh
#Copy and paste as app.jar
COPY --from=builder /app/gradle/src/build/libs/eureka-discovery-service-0.0.1.jar app.jar
#update when it is changed
RUN bash -c 'touch /app.jar'
#RunTime Arguments=>SecureRandom class will be urandom. It is required for linux. Profile: container
ENTRYPOINT [ "java", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=container", "-jar", "/app.jar" ]