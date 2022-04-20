FROM openjdk:19-ea-16-slim
# FROM openjdk:16-slim-buster
ARG JAR_FILE=target/DukesHiddenStash-1.0-SNAPSHOT.jar
ARG JAR_LIB_FILE=target/lib/
RUN echo $(java --version)
RUN mkdir -p /usr/src/app
# cd /usr/src/app
WORKDIR /usr/src/app
# Check, if jar is there
RUN if [[ -z "$JAR_FILE" ]] ; then echo "[DOCKER] Jar file is missing."; fi
# RUN echo $(ls)
# RUN echo $(pwd)
COPY * /usr/src/app/
# apk is alpine only
RUN apk add --no-cache maven
RUN mvn clean install test package

COPY target/DukesHiddenStash-1.0-SNAPSHOT.jar ToRun.jar
# EXPOSE 8080
RUN echo $(ls)
RUN echo $(pwd)
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "ToRun.jar"]
