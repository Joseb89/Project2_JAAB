pipeline {

    environment {
        dockerHubCreds = 'DockerHubCreds'
        mainDockerImage = ''
        patientDockerImage = ''
        doctorDockerImage = ''
        pharmacyDockerImage = ''
    }

    agent any

    stages {
      stage('Build') {
        steps {
           dir('main_api'){
              withMaven {
                 sh 'mvn clean -DskipTests package'
              }

              script {
                mainDockerImage = docker.build "joseb89/project2mainapi"
              }
           }
           dir('patient_api'){
              withMaven {
                  sh 'mvn clean -DskipTests package'
              }

              script {
                patientDockerImage = docker.build "joseb89/project2patientapi"
              }
           }
           dir('doctor_api'){
              withMaven {
                  sh 'mvn clean -DskipTests package'
              }

              script {
                doctorDockerImage = docker.build "joseb89/project2doctorapi"
              }
           }
           dir('pharmacy_api'){
              withMaven {
                   sh 'mvn clean -DskipTests package'
              }

              script {
                pharmacyDockerImage = docker.build "joseb89/project2pharmacyapi"
              }
           }
        }
      }

      stage("Deploy To Docker Hub") {
        steps  {
            script {
               docker.withRegistry('', 'DockerHubCreds') {
                    mainDockerImage.push("$BUILD_NUMBER")
                    mainDockerImage.push('latest')

                    patientDockerImage.push("$BUILD_NUMBER")
                    patientDockerImage.push('latest')

                    doctorDockerImage.push("$BUILD_NUMBER")
                    doctorDockerImage.push('latest')

                    pharmacyDockerImage.push("$BUILD_NUMBER")
                    pharmacyDockerImage.push('latest')
               }
            }
        }
      }

      stage("Remove Docker Images") {
        steps {
           script {
             sh "docker rmi joseb89/project2mainapi:$BUILD_NUMBER"
             sh 'docker rmi joseb89/project2mainapi:latest'
             sh "docker rmi joseb89/project2patientapi:$BUILD_NUMBER"
             sh 'docker rmi joseb89/project2patientapi:latest'
             sh "docker rmi joseb89/project2doctorapi:$BUILD_NUMBER"
             sh 'docker rmi joseb89/project2doctorapi:latest'
             sh "docker rmi joseb89/project2pharmacyapi:$BUILD_NUMBER"
             sh 'docker rmi joseb89/project2pharmacyapi:latest'
           }
        }
      }
   }
}
