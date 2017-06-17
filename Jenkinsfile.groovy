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
        docker.withRegistry(env.REGISTRY_HOST, 'docker_registry_credentials_id') {
            stage('Push Docker Image') {
                steps {
                    echo 'Pushing docker image....'
                    sh '/usr/local/bin/docker push $REGISTRY_HOST/kube-demo-plugin'
                }
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
