def application = "ansible-playbook"
pipeline {
  agent any

  stages {
        stage('clone') {
          steps {
          script{
            checkout([$class: 'GitSCM', branches: [[name: 'master']], userRemoteConfigs: [[url: 'https://github.com/AamirKhan511/Ansible_playbook.git']]], 
                 extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: '/home/ubuntu/ansible_playbooks/checkoutdirectory']])
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
