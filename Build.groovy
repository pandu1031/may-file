pipeline {
    agent { label 'slave1' } 
    stages {
        stage('Build') {
            steps {
                // Define build steps here
                echo "Building on agent 'slave1'"
            }
        }
    }
}
