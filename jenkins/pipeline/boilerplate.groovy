pipeline {
    agent any

    environment {
        APPLICATION_SOURCE_URL = 'https://github.com/freddieentity/application-code.git'
    }
    stages {
        stage ('git checkout') {

            steps {
                echo 'git checkout'            
                
                git branch: 'main',
                    url: ${APPLICATION_SOURCE_URL}
            }
        }

        stage('UnitTest') {
            steps {
                echo 'UnitTesting..'
            }
        }
        stage('SCA') {
            steps {
                echo 'SCA..'
            }
        }
        stage('SAST') {
            steps {
                echo 'SASTing..'
            }
        }
        stage('Build') {
            steps {
                echo 'Building..'
            }
        }
        stage('ContainerImageScan') {
            steps {
                echo 'ContainerImageScanning..'
            }
        }
        stage('Deploy') { 
            steps {
                echo 'Deploying to DEV....'
            }
        }
    }
    post { 
        always { 
            cleanWs()
        }
    }
}