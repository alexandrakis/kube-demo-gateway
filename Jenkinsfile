pipeline {
    agent any

    stages {
        stage('Test') {
            steps {
                echo 'Testing...'
                sh mvn clean test
            }
        }
        stage('Build Jar') {
            steps {
                echo 'Building Jar file...'
                sh mvn clean package
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
        }
        stage('Build Docker Image') {
            steps {
                echo 'Building docker image....'
                sh docker build -t dockreg.datamation.gr/kube-demo-plugin .
            }
        }
        stage('Push Docker Image') {
            steps {
                echo 'Pushing docker image....'
                sh docker push dockreg.datamation.gr/kube-demo-plugin
            }
        }
        stage('Deploy to Kybernetes') {
            steps {
                echo 'Deploying....'
            }
        }
        stage('Service to Kybernetes') {
            steps {
                echo 'Servicing....'
            }
        }
    }
}