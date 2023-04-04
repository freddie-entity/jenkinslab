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
                echo 'SAST..'
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
        stage('ImageUpdate') { 
            steps {
                echo 'ImageUpdateing....'
            }
        }
    }
}