# Jenkinslab
For separated lifecycle, each server will be hosted using its own docker-compose file
## Slapd Server
* LDAP with mock users
## Jenkins Server
* JCasC + JobDSL + Pipeline -> Automate all the Jenkins bootstrapping phase
## Nexus Server
* Artifact repository manager
## Sonarqube Server 
* SAST server
* Postgres controls Sonarqube data
## Java Agent
* Jenkins agent for job running
## GitLab Server
* Self-hosted git server

