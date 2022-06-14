
### STAGE 1: BUILD ###
FROM openjdk:11 AS build
WORKDIR /appBE
# copy files from local machine to virtual directory in docker
COPY . .
RUN cd /appBE
RUN mvn clean package
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar", "/app.jar"]

### STAGE 2:RUN ###
# Defining nginx image to be used
#FROM nginx:1.17.1-alpine AS ngi
## Copying compiled code and nginx config to different folder
#COPY /nginx.conf  /etc/nginx/conf.d/default.conf

# COPY dist/ant-app /var/www
# COPY /nginx.conf /etc/nginx/nginx.conf
# EXPOSE 8094
