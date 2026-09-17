pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "ghcr.io/rajenderg/shopping-cart"
        DOCKER_CREDENTIALS = credentials('ghcr-credentials')
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & test') {
            steps {
                sh 'chmod +x gradlew'
                sh './gradlew build'
            }
        }

        stage('Build docker image') {
            steps {
                sh './gradlew bootJar'
                sh 'docker build -t $DOCKER_IMAGE:latest .'
            }
        }

        stage('Push docker image') {
            steps {
                sh 'echo $DOCKER_CREDENTIALS_PSW | docker login ghcr.io -u $DOCKER_CREDENTIALS_USR --password-stdin'
                sh 'docker push $DOCKER_IMAGE:latest'
            }
        }
    }

    post {
        always {
            junit '**/build/test-results/test/*.xml'
        }
    }
}
