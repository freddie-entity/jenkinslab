FROM jenkins/jenkins:lts
ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false
ENV CASC_JENKINS_CONFIG /var/jenkins_home/jenkins.yaml
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN jenkins-plugin-cli --plugin-file /usr/share/jenkins/ref/plugins.txt
COPY jenkins.yaml /var/jenkins_home/jenkins.yaml

# Install CLI tools for Jenkins to interact with external systems
# Then restrict the Jenkins to use its user only
# USER jenkins