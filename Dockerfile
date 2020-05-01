FROM openjdk:11-jdk-slim

COPY target/project-service-1.0.jar /opt/project-service/

# some tools
RUN apt-get update && apt-get install -y vim tree mc lnav

# setting proper TZ
ENV TZ=Europe/Moscow
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

EXPOSE 8080
VOLUME /opt/project-service/logs

WORKDIR /opt/project-service/

CMD ["java","-jar","project-service-1.0.jar"]