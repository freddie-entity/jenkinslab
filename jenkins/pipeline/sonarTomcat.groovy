pipeline {
    agent any
    tools {
        jdk 'OpenJDK-11'
        maven 'maven3'
    }

    stages {
        stage ('SCM') {
            steps {
                git branch: 'main', changelog: false, credentialsId: 'gitlab-cred', poll: false, url: 'https://gitlab.com/devops'
            }
        stage('Compile') {
            steps {
                sh "mvn clean compile"
            }            
        stage('Sonarqube Analysis') {
            steps {
                withSonarQubeEnv('sonar-server')
                sh '''
                    mvn sonar:sonar 
                    -Dsonar.projectName=Petclinic
                    -Dsonar.host.url=http://localhost:9000 -Dsonar.login=hash
                    -Dsonar.java.binaries=.
                    -Dsonar.projectKey=myproject
                    '''
            }
        }
        stage ('Build') {
            steps {
                sh "mvn clean install"
            }
        }
        stage ('Deploy to Tomcat') {
            steps {
                sh "cp /var/lib/jenkins/workspace/Pipeline/target/petclinic.war /opt./"
            }
        }
    }
}