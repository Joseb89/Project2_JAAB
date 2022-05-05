pipeline {
    agent any

    stages {
      stage('Build') {
        steps {
           dir('main_api'){
              withMaven {
                 sh 'mvn clean compile -DskipTests package'
              }
           }
           dir('patient_api'){
              withMaven {
                  sh 'mvn clean compile -DskipTests package'
              }
           }
           dir('doctor_api'){
              withMaven {
                  sh 'mvn clean compile -DskipTests package'
              }
           }
           dir('pharmacy_api'){
              withMaven {
                   sh 'mvn clean compile -DskipTests package'
              }
           }
        }
      }
   }
}
