FROM jenkins/jenkins:lts
USER root

RUN apt-get update -qq -y && \
    apt-get install yq unzip wget -qqy apt-transport-https ca-certificates curl gnupg2 software-properties-common -y 

RUN curl -fsSL https://download.docker.com/linux/debian/gpg | apt-key add -
RUN add-apt-repository \
   "deb [arch=amd64] https://download.docker.com/linux/debian \
   $(lsb_release -cs) \
   stable"

RUN apt-get update  -qq \
    && apt-get -y install docker-ce

# install terraform 1.4.4
RUN wget https://releases.hashicorp.com/terraform/1.4.4/terraform_1.4.4_linux_amd64.zip && \
    unzip terraform_1.4.4_linux_amd64.zip && \
    mv terraform /usr/local/bin/

RUN apt update -y && \
    apt install ansible -y

RUN curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl" && \
    chmod +x kubectl && \
    mv kubectl /usr/local/bin

ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false
ENV CASC_JENKINS_CONFIG /var/jenkins_home/jenkins.yaml
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN jenkins-plugin-cli --plugin-file /usr/share/jenkins/ref/plugins.txt
COPY jenkins.yaml /var/jenkins_home/jenkins.yaml
# Install CLI tools for Jenkins to interact with external systems
# Then restrict the Jenkins to use its user only
# RUN usermod -aG docker jenkins
# USER jenkins