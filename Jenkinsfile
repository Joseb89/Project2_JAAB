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
        }
      }
   }
}
