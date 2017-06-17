node {
    stage 'Test'
        echo 'Testing...'
        sh '/usr/local/bin/mvn clean test'
    stage 'Build Jar'
        echo 'Building Jar file...'
        sh '/usr/local/bin/mvn clean package'
        archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
        junit 'target/surefire-reports/*.xml'
    docker.withRegistry(env.REGISTRY_HOST, 'docker_registry_credentials_id') {
        stage 'Build Docker Image'
        echo 'Building docker image....'
        def img = docker.build("kube-demo-gateway:${env.BUILD_TAG}", '.')
        stage 'Push Docker Image'
        echo 'Pushing docker image....'
        img.push('latest')
    }
    stage 'Deploy to Kubernetes'
        echo 'Deploying....'
    stage 'Service to Kubernetes'
        echo 'Servicing....'
}
