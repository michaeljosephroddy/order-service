pipeline {
    agent any
    environment {
        JAVA_HOME = tool 'JDK21' // The name you set in Jenkins UI
        PATH = "${JAVA_HOME}/bin:${PATH}"
    }

    stages {
        stage('Check Java Version') {
            steps {
                sh 'java -version'
            }
        }

        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/michaeljosephroddy/online-store-service.git'
            }
        }

        stage('Build with Maven and Static Code Analysis') {
            steps{
                withSonarQubeEnv('SonarQube') {
                    sh "mvn clean package verify sonar:sonar -Dsonar.projectKey=sonar-project-local -Dsonar.projectName='sonar-project-local'"
                }
            }
        }

        stage('Create Docker Image and Push Docker Image to DockerHub') {
            steps {
                sh 'docker build -t michaelroddy04/online-store-service .'
                 withDockerRegistry([credentialsId: 'dockerhub-credentials', url: '']) {
                    sh 'docker tag online-store-service michaelroddy04/online-store-service'
                    sh 'docker push michaelroddy04/online-store-service'
                 }
            }
        }

        stage('Run Ansible for Automated Deployment') {
            steps {
                sh 'sudo chmod 400 lab1webserverkeypair.pem'
                sh 'ansible-playbook -i inventory.ini deploy.yml
'
            }
        }

    }
}