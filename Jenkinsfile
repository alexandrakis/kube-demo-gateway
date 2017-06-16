pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out...'
                checkout scm
            }
        }
        stage('Test') {
            steps {
                echo 'Testing...'
                sh '/usr/local/bin/mvn clean test'
            }
        }
        stage('Build Jar') {
            steps {
                echo 'Building Jar file...'
                sh '/usr/local/bin/mvn clean package'
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
        }
        stage('Build Docker Image') {
            steps {
                echo 'Building docker image....'
                sh '/usr/local/bin/docker build -t dockreg.datamation.gr/kube-demo-plugin .'
            }
        }
        stage('PuDocker Image') {
            steps {
                echo 'Pushing docker image....'
                sh '/usr/local/bin/docker push dockreg.datamation.gr/kube-demo-plugin'
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