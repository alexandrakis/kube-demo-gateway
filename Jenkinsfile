pipeline {
    agent any

    stages {
        stage('Test') {
            steps {
                echo 'Testing...'
                sh script:"mvn clean test"
            }
        }
        stage('Build Jar') {
            steps {
                echo 'Building Jar file...'
                sh script:"mvn clean package"
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
        }
        stage('Build Docker Image') {
            steps {
                echo 'Building docker image....'
                sh script:"docker build -t dockreg.datamation.gr/kube-demo-plugin ."
            }
        }
        stage('PuDocker Image') {
            steps {
                echo 'Pushing docker image....'
                sh script:"docker push dockreg.datamation.gr/kube-demo-plugin"
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