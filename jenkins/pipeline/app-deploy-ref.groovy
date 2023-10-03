pipeline {
    agent any

    environment {
        CONTAINER_REPOSITORY= "freddieentity"
        APPLICATION_SOURCE_URL = 'https://github.com/freddieentity/nginx-service.git'
        APPLICATION_NAME = "nginx-service"
        CONTAINER_BUILD_CONTEXT = "."
    }
    stages {
        stage ('Git Checkout') {

            steps {
                echo 'Git Checkout'            
                git branch: 'main',
                    url: "${APPLICATION_SOURCE_URL}"
                    // credentialsId: 'githubToken'
                script {
                GIT_COMMIT = sh(returnStdout: true, script: "git log -n 1 --pretty=format:'%h'").trim()
                }
            }
        }
        stage('Build') {
            steps {
                echo 'Building..'
                withDockerRegistry([credentialsId: "CONTAINER_RESIGTRY_CREDENTIALS", url: ""]) {
                    sh "docker build -t ${CONTAINER_REPOSITORY}/${APPLICATION_NAME}:${GIT_COMMIT} ${CONTAINER_BUILD_CONTEXT}"
                    sh "docker push ${CONTAINER_REPOSITORY}/${APPLICATION_NAME}:${GIT_COMMIT}"
                }
            }
        }
        stage('Deploy') { 
            steps {
                echo 'Deploying....'
            }
        }
    }
    post {
        always { 
            cleanWs()
        }
    }
}