pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/michaeljosephroddy/online-store-service.git'
            }
        }

        stage('Build with Maven and Static Code Analysis') {
            steps{
                withSonarQubeEnv('SonarQube') {
                    sh "mvn clean verify sonar:sonar -Dsonar.projectKey=sonar-project-local -Dsonar.projectName='sonar-project-local'"
                }
            }
        }

        stage('Create Docker Image and Push Docker Image to DockerHub') {
            steps {
                sh 'docker-compose build online-store-service'
                 withDockerRegistry([credentialsId: 'docker-hub-credentials', url: '']) {
                    sh 'docker tag online-store-service michaelroddy04/online-store-service'
                    sh 'docker push michaelroddy04/online-store-service'
                 }
            }
        }

//         just making a change to test auto build trigger
    }
}