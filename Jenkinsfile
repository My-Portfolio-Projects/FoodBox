pipeline {
    agent any

    stages {
        stage('Build Angular App') {
            steps {
                script {
                    // Move into the frontend directory
                    dir('frontend') {
                        sh 'npm install'
                        sh 'ng build'
                    }
                }
            }
        }

        stage('Build Spring Boot App') {
            steps {
                script {
                    // Move into the backend directory
                    dir('backend') {
                        sh './mvnw clean package'
                    }
                }
            }
        }

        stage('Build Docker Images') {
            steps {
                script {
                    // Build Angular Docker image
                    dir('frontend') {
                        sh 'docker build -t angular-app:latest .'
                    }

                    // Build Spring Boot Docker image
                    dir('backend') {
                        sh 'docker build -t spring-boot-app:latest .'
                    }
                }
            }
        }

        stage('Deploy Docker Containers') {
            steps {
                script {
                    // Use Docker Compose to run the containers
                    sh 'docker-compose up -d'
                }
            }
        }
    }
}
