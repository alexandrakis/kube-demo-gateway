pipeline {
    agent any

    stages {
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
                junit 'target/surefire-reports/*.xml'
            }
        }
        stage('Build Docker Image') {
            steps {
                echo 'Building docker image....'
                sh '/usr/local/bin/docker build -t kube-demo-plugin .'
                echo 'Taging docker image....'
                sh '/usr/local/bin/docker build -t $REGISTRY_HOST/kube-demo-plugin .'
            }
        }
        stage('Push Docker Image') {
            steps {
                echo 'Pushing docker image....'
                sh '/usr/local/bin/docker login ${env.REGISTRY_HOST} -u ${env.REGISTRY_USERNAME} -p ${env.REGISTRY_PASSWORD}'
                sh '/usr/local/bin/docker push ${env.REGISTRY_HOST}/kube-demo-plugin'
            }
        }
        stage('Deploy to Kubernetes') {
            steps {
                echo 'Deploying....'
            }
        }
        stage('Service to Kubernetes') {
            steps {
                echo 'Servicing....'
            }
        }
    }
}
