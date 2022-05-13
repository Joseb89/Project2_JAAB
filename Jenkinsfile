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
                sh "sudo docker build -t joseb89/project2mainapi:${BUILD_NUMBER}"
              }
           }
           dir('patient_api'){
              withMaven {
                  sh 'mvn clean compile -DskipTests package'
              }

              script {
                sh "sudo docker build -t joseb89/project2patientapi:${BUILD_NUMBER}"
              }
           }
           dir('doctor_api'){
              withMaven {
                  sh 'mvn clean compile -DskipTests package'
              }

              script {
                sh "sudo docker build -t joseb89/project2doctorapi:${BUILD_NUMBER}"
              }
           }
           dir('pharmacy_api'){
              withMaven {
                   sh 'mvn clean compile -DskipTests package'
              }

              script {
                sh "sudo docker build -t joseb89/project2pharmacyapi:${BUILD_NUMBER}"
              }
           }
        }
      }

      stage("Remove Docker Images") {
        steps {
           script {
             sh "sudo docker rmi joseb89/project2mainapi:${BUILD_NUMBER}"
             sh "sudo docker rmi joseb89/project2patientapi:${BUILD_NUMBER}"
             sh "sudo docker rmi joseb89/project2doctorapi:${BUILD_NUMBER}"
             sh "sudo docker rmi joseb89/project2pharmacyapi:${BUILD_NUMBER}"
           }
        }
      }
   }
}
