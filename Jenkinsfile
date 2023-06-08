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
            sudo docker push aamir335/nginx:${BUILD_NUMBER}
               #ansible-palybook nginx-playbook.yml
            docker rm -f \$(docker ps -a -f name=aamir335/nginx -q) || true
              ansible-playbook -i ansible.cfg nginx-playbook.yml -b
          '''
        }
        }
        }

  }
}
