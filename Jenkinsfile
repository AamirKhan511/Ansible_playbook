def application = "ansible-playbook"
pipeline {
  agent any
environment {
    DOCKERHUB_CREDENTIALS = credentials('dockerhub')
  }
  stages {
        stage('clone') {
          steps {
          script{
            checkout([
  $class: 'GitSCM',
  branches: [
    [name: 'master']
  ],
  userRemoteConfigs: [
    [url: 'https://github.com/AamirKhan511/Ansible_playbook.git']
  ],
  extensions: [
    [$class: 'RelativeTargetDirectory', relativeTargetDir: '/etc/checkoutdirectory']
  ]
])
         sh '''
          #!/bin/bash
            cd /etc/checkoutdirectory/
            docker_tag=${BUILD_NUMBER}
            echo $docker_tag
            echo " Starting build docker image"
            sudo docker build -t aamir335/nginx:${docker_tag} .  || exit 1
            echo $DOCKERHUB_CREDENTIALS_PSW | sudo docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin
            sudo docker push aamir335/nginx:${docker_tag}
            ansible-playbook -i ansible.cfg nginx-playbook.yml -b --extra-vars "imagename=${docker_tag}"
            docker rmi aamir335/ngnix:${docker_tag} -f || true
          '''
        }
        }
        }

  }
post {
    always {
      sh 'sudo docker logout'
    }
  }
}
