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

wget https://binaries.sonarsource.com/Distribution/sonar-scanner-cli/sonar-scanner-cli-4.2.0.1873-linux.zip
unzip sonar-scanner-cli-4.2.0.1873-linux.zip
mv sonar-scanner-4.2.0.1873-linux /opt/sonar-scanner