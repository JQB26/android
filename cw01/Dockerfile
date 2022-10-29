FROM ubuntu:22.04

# setup
RUN apt update && apt install -y unzip curl wget zip vim

RUN apt-get update && apt-get install -y tzdata sudo && \
        apt-get clean && \
 	rm -rf /var/lib/apt/lists/*
ENV TZ="Europe/Warsaw"


# python 3.8
RUN apt-get update && \
	apt-get install -y software-properties-common && \
	add-apt-repository ppa:deadsnakes/ppa && \
	apt-get install -y python3.8 && \
        apt-get clean && \
 	rm -rf /var/lib/apt/lists/*

# java
RUN apt-get update && apt-get install -y openjdk-8-jdk

# kotlin
RUN curl -s https://get.sdkman.io | bash
RUN /bin/bash -c "source /root/.sdkman/bin/sdkman-init.sh; sdk version; sdk install kotlin"

ENV KOTLIN_HOME=/root/.sdkman/candidates/kotlin/current/bin
ENV PATH=$PATH:$KOTLIN_HOME

# gradle
RUN bash -c "source /root/.sdkman/bin/sdkman-init.sh && sdk install gradle 7.3"

ENV GRADLE_HOME=/root/.sdkman/candidates/gradle/current/bin
ENV PATH=$PATH:$GRADLE_HOME

# gradle project
WORKDIR /home/
RUN bash -c "gradle init --type kotlin-application"
COPY /files/build.gradle.kts /home/app/build.gradle.kts

# run with gradle
RUN bash -c "./gradlew run"

# run with command line
RUN bash -c "kotlinc app/src/main/kotlin/home/App.kt -include-runtime -d hello.jar"
RUN bash -c "java -jar hello.jar"
