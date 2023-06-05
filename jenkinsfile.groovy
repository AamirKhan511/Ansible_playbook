def application = "devops"
pipeline {
  agent any
  environment {
    DOCKERHUB_CREDENTIALS = credentials('dockerhub')
  }
  stages {
       stage('clone') {
      steps {
        git 'https://github.com/AamirKhan511/Ansible_playbook.git'
           sh '''
          #!/bin/bash
          cd /etc/ansible/
          ansible-playbook -i ansible.cfg nginx-playbook.yml -b

          '''
      }
    }
  }
  post {
    always {
      sh 'sudo docker logout'
    }
  }
}

