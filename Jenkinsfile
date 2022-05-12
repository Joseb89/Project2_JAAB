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
                dockerImage = docker.build 'joseb89/project2mainapi'
              }
           }
           dir('patient_api'){
              withMaven {
                  sh 'mvn clean compile -DskipTests package'
              }

              script {
                dockerImage = docker.build 'joseb89/project2patientapi'
              }
           }
           dir('doctor_api'){
              withMaven {
                  sh 'mvn clean compile -DskipTests package'
              }

              script {
                dockerImage = docker.build 'joseb89/project2doctorapi'
              }
           }
           dir('pharmacy_api'){
              withMaven {
                   sh 'mvn clean compile -DskipTests package'
              }

              script {
                dockerImage = docker.build 'joseb89/project2pharmacyapi'
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
