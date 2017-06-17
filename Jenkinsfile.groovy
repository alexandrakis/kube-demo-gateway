node {
    stage 'Check out'
    echo 'Checking out...'
    checkout scm
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
        String imageName = "kube-demo-gateway:${env.BUILD_NUMBER}"
        sh "docker build -t ${imageName}  ."
        def img = docker.image(imageName)
        stage 'Push Docker Image'
        echo 'Pushing docker image....'
        img.push('latest')
    }
    stage 'Deploy to Kubernetes'
        echo 'Deploying....'
        sh 'pwd'
        sh 'helm install --dry-run ./helm'
}
