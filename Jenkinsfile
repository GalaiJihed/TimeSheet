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
        bat 'mvn sonar:sonar -Dsonar.host.url=http://localhost:9000 '
      }
    }

  }
}