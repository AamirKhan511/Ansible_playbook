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
            docker build -t aamir335/nginx:${BUILD_NUMBER} .
            input message: 'Do you want to approve the deployment?', ok: 'Yes'
            echo $DOCKERHUB_CREDENTIALS_PSW | sudo docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin
            sudo docker push aamir335/nginx:${BUILD_NUMBER}
            docker rm -f \$(docker ps -a -f name=ansible-playbook -q) || true
              ansible-playbook -i ansible.cfg nginx-playbook.yml -b
            '''
        }
        }
        }
  }
}
