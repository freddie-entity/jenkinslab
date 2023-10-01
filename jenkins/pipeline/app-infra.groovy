pipeline {
    agent any

    environment {
        APPLICATION_SOURCE_URL = 'https://github.com/freddieentity/terraform-ansible.git'
        AWS_ACCESS_KEY_ID = credentials('AWS_ACCESS_KEY_ID')
        AWS_SECRET_ACCESS_KEY = credentials('AWS_SECRET_ACCESS_KEY')
        // AWS_REGION = "ap-southeast-1"
        TERRAFORM_DIR = "terraform"
    }
    stages {
        stage ('Git Checkout') {

            steps {
                echo 'Git Checkout'
                
                git branch: 'main',
                    url: "${APPLICATION_SOURCE_URL}"
            }
        }
        stage ('Init') {

            steps {
                echo 'Terraform Init'
                                
                dir("${TERRAFORM_DIR}") {
                    script {
                        sh 'terraform init -no-color'
                    }
                }
            }
        }
        
        stage ('Plan') {

            steps {
                echo 'Terraform Plan'

                dir("${TERRAFORM_DIR}") {
                    script {
                        sh 'terraform plan -out=tfplan -input=false -no-color'
                    }
                }  
            }
        }
        
        stage ('Wait for approval (Apply)') {
            steps {
                timeout(time: 30, unit: 'MINUTES') {
                    script {
                        env.RELEASE_TO_PROD =   input message: 'Please approve Terraform plan', 
                                                parameters: [string(description: 'Type in \'yes\' if you want to deploy this terraform build in production. Only \'yes\' will be accepted to approve.', 
                                                name: 'Promote to production?', 
                                                trim: true)]
                    }
                }

                echo "User input: ${env.RELEASE_TO_PROD}"
            }

        }

        stage ('Apply') {

            when {
                expression {
                    env.RELEASE_TO_PROD == 'yes'
                }
            }

            steps {
                echo 'Terraform Apply'

                dir("${TERRAFORM_DIR}") {
                    script {
                        sh 'terraform apply -auto-approve -no-color tfplan'
                    }
                }
            }
        }
    
        stage ('Wait for approval (Destroy)') {
            steps {
                timeout(time: 30, unit: 'MINUTES') {
                    script {
                        env.DESTROY =   input message: 'Please approve Terraform destroy', 
                                                parameters: [string(description: 'Type in \'yes\' if you want to destroy this terraform build. Only \'yes\' will be accepted to approve.', 
                                                name: 'Destroy?', 
                                                trim: true)]
                    }
                }

                echo "User input: ${env.DESTROY}"
            }

        }

        stage ('Destroy') {

            when {
                expression {
                    env.DESTROY == 'yes'
                }
            }

            steps {
                echo 'Destroy'
                
                dir("${TERRAFORM_DIR}") {
                    script {
                    sh 'terraform destroy -auto-approve -no-color'
                    }   
                }
            }
        }
    }
    post { 
        always { 
            cleanWs()
        }
    }
}