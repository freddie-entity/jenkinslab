pipeline {
    agent any

    stages {
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
}