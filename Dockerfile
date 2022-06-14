
### STAGE 1: BUILD ###
FROM maven:3.8.5-openjdk-11 AS builders
# create app directory in images and copies pom.xml into it
COPY pom.xml /app/
# copies src directory into the app directort in image
COPY src /app/src
# sets app as the directory into the app
WORKDIR /app/
# run mvn
RUN mvn clean package

FROM openjdk:11
WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY --from=builders /app/target/app.jar /app/
ENTRYPOINT ["java","-jar", "app.jar"]

### STAGE 2:RUN ###
# Defining nginx image to be used
#FROM nginx:1.17.1-alpine AS ngi
## Copying compiled code and nginx config to different folder
#COPY /nginx.conf  /etc/nginx/conf.d/default.conf

# COPY dist/ant-app /var/www
# COPY /nginx.conf /etc/nginx/nginx.conf
# EXPOSE 8094
