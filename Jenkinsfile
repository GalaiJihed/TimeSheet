pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        git(url: 'https://github.com/GalaiJihed/TimeSheet.git', branch: 'master', poll: true, credentialsId: 'GalaiJihed')
        sh '''
mvn sonar:sonar  -Dsonar.host.url=http://localhost:8080-Dsonar.login=411938cabe83a5216e99be8265b8ba5cffb841a9 -Dlicense.skip=true


'''
      }
    }

  }
}