pipeline {
    agent any

    stages {
        stage('Build Angular App') {
            steps {
                script {
                    // Move into the frontend directory
                    dir('frontend') {
                        bat 'npm.cmd install'
                        bat 'npm.cmd run build'
                    }
                }
            }
        }

        stage('Build Spring Boot App') {
            steps {
                script {
                    // Move into the backend directory
                    dir('backend') {
                        bat './mvnw clean package'
                    }
                }
            }
        }

        stage('Build Docker Images') {
            steps {
                script {
                    // Build Angular Docker image
                    dir('frontend') {
                        bat'docker build -t angular-app:latest .'
                    }

                    // Build Spring Boot Docker image
                    dir('backend') {
                        bat'docker build -t spring-boot-app:latest .'
                    }
                }
            }
        }

        stage('Deploy Docker Containers') {
            steps {
                script {
                    // Use Docker Compose to run the containers
                    bat'docker-compose up -d'
                }
            }
        }
    }
}
