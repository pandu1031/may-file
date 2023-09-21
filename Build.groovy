pipeline {
    agent { label 'slave1' } // Specifies the agent label
    stages {
        stage('Build') {
            steps {
                // Define build steps here
                echo "Building on agent 'slave1'"
            }
        }
    }
}
