pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        git(url: 'https://github.com/GalaiJihed/TimeSheet.git', branch: 'master', poll: true, credentialsId: 'GalaiJihed')
      }
    }

    stage('Step Compile') {
      steps {
        sh 'mvn clean compile'
      }
    }

  }
}