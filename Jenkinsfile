pipeline {

    environment {
        dockerHubCreds = 'DockerHubCreds'
        dockerImage = ''
    }

    agent any

    stages {
      stage('Build') {
        steps {
           dir('main_api'){
              withMaven {
                 sh 'mvn clean compile -DskipTests package'
              }

              script {
                sh 'docker version'
                dockerImage = docker.build "joseb89/project2mainapi:latest"
              }
           }
           dir('patient_api'){
              withMaven {
                  sh 'mvn clean compile -DskipTests package'
              }

              script {
                dockerImage = docker.build "joseb89/project2patientapi:latest"
              }
           }
           dir('doctor_api'){
              withMaven {
                  sh 'mvn clean compile -DskipTests package'
              }

              script {
                dockerImage = docker.build "joseb89/project2doctorapi:latest"
              }
           }
           dir('pharmacy_api'){
              withMaven {
                   sh 'mvn clean compile -DskipTests package'
              }

              script {
                dockerImage = docker.build "joseb89/project2pharmacyapi:latest"
              }
           }
        }
      }

      stage("Remove Docker Images") {
        steps {
            sh 'docker rmi joseb89/project2mainapi:latest'
            sh 'docker rmi joseb89/project2patientapi:latest'
            sh 'docker rmi joseb89/project2doctorapi:latest'
            sh 'docker rmi joseb89/project2pharmacyapi:latest'
        }
      }
   }
}
