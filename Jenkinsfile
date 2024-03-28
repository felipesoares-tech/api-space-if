pipeline {
    agent any
    
    stages {
        stage ('Build Image') {
            steps {
                script {
                    dockerapp = docker.build("felipesoares-tech/api-auth", '-f ./src/Dockerfile ./src')
                } 
            } 
        }
    }	       
}
