def application = "ansible-playbook"
pipeline {
  agent any

  stages {
        stage('clone') {
          steps {
          script{
            checkout([$class: 'GitSCM', branches: [[name: 'master']], userRemoteConfigs: [[url: 'https://github.com/AamirKhan511/Ansible_playbook.git']]])
            sh '''
          #!/bin/bash
            cd /etc/ansible/
             sudo ansible-playbook -i ansible.cfg nginx-playbook.yml -b
            '''
        }
        }
        }
  }
}

