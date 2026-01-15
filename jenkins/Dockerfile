FROM jenkins/jenkins:lts-jdk17
USER root

RUN apt-get update \
 && apt-get install -y --no-install-recommends \
      docker.io docker-compose ca-certificates curl \
 && rm -rf /var/lib/apt/lists/*

USER jenkins
