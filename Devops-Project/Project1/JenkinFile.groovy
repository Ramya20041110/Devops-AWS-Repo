pipeline {
    agent any

    environment {
        IMAGE_NAME = 'ecom-image'
        AWS_REGION = 'ap-south-1'
        ECR_REPOSITORY = 'student-data'
        ECS_CLUSTER = 'deployment'
        ECS_SERVICE = 'dependable-server-b58r5m-service-uvc2il66'
        CONTAINER_NAME = 'app'
    }

    stages {

        stage('Pull Source Code from GitHub') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/Ramya20041110/DevSeco-Pro-ecommerce'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t ${IMAGE_NAME}:latest .'
            }
        }

        stage('Run Docker Container') {
            steps {
                sh '''
                    docker rm -f ecommerce-app || true

                    docker run -d --name ecommerce-app \
                        -p 5000:5000 ${IMAGE_NAME}:latest
                '''
            }
        }

        stage('Login to ECR and Push Image') {
            steps {
                script {
                    env.AWS_ACCOUNT_ID = sh(
                        script: 'aws sts get-caller-identity --query Account --output text',
                        returnStdout: true
                    ).trim()

                    env.ECR_REGISTRY = "${env.AWS_ACCOUNT_ID}.dkr.ecr.${env.AWS_REGION}.amazonaws.com"

                    env.IMAGE_URI = "${env.ECR_REGISTRY}/${env.ECR_REPOSITORY}:${env.BUILD_NUMBER}"
                }

                sh '''
                    aws ecr get-login-password --region $AWS_REGION |
                    docker login --username AWS --password-stdin $ECR_REGISTRY

                    docker tag ${IMAGE_NAME}:latest $IMAGE_URI

                    docker push $IMAGE_URI

                    docker logout $ECR_REGISTRY
                '''
            }
        }

        stage('Deploy to ECS') {
            steps {
                sh '''
                    aws ecs update-service \
                        --cluster $ECS_CLUSTER \
                        --service $ECS_SERVICE \
                        --force-new-deployment \
                        --region $AWS_REGION

                    aws ecs wait services-stable \
                        --cluster $ECS_CLUSTER \
                        --services $ECS_SERVICE \
                        --region $AWS_REGION
                '''
            }
        }
    }
}
