pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        git(url: 'https://github.com/GalaiJihed/TimeSheet.git', branch: 'master', poll: true, credentialsId: 'GalaiJihed')
      }
    }

    stage('Testing Stage') {
      steps {
        sh 'mvn sonar:sonar -Dsonar.host.url=http://localhost:8081-Dlicense.skip=true'
      }
    }

  }
}