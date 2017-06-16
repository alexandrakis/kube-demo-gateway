pipeline {
    agent any

    node {
        checkout scm
    }
    stages {
        stage('Test') {
            steps {
                echo 'Testing...'
                mvn clean test
            }
        }
        stage('Test') {
            steps {
                echo 'Testing...'
                mvn clean test
            }
        }
        stage('Build Jar') {
            steps {
                echo 'Building Jar file...'
                mvn clean package
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
        }
        stage('Build Docker Image') {
            steps {
                echo 'Building docker image....'
                docker build -t dockreg.datamation.gr/kube-demo-plugin .
            }
        }
        stage('PuDocker Image') {
            steps {
                echo 'Pushing docker image....'
                docker pudockreg.datamation.gr/kube-demo-plugin
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