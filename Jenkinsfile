pipeline {
    agent any

    node {
        checkout scm
    }
    stages {
        stage('Test') {
            steps {
                echo 'Testing...'
                sh cmd:"mvn clean test", name:"Running JUnit Tests"
            }
        }
        stage('Build Jar') {
            steps {
                echo 'Building Jar file...'
                sh cmd:"mvn clean package", name:"Packaging the jar"
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
        }
        stage('Build Docker Image') {
            steps {
                echo 'Building docker image....'
                sh cmd:"docker build -t dockreg.datamation.gr/kube-demo-plugin .", name:"Building docker image"
            }
        }
        stage('PuDocker Image') {
            steps {
                echo 'Pushing docker image....'
                sh cmd:"docker push dockreg.datamation.gr/kube-demo-plugin", name:"Push docker image"
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