pipeline {
    agent { label 'slave1' } 
    stages {
        stage("clone code"){
            steps{
                println "Here im cloning the code from github"
                git branch: '$BRANCH_NAME',
                      url: 'https://github.com/pandu1031/boxfuse-sample-java-war-hello.git'

            }
        }
        stage("Build"){
            steps{
                println "here im building the code"
                sh "mvn clean package"
                sh "ls -l target"
                echo "Building on agent 'slave1'"
            }
        }
        stage("uploading artifacts"){
            steps{
                println "here im uploading artifacts to s3 bucket"
                sh "aws s3 ls"
                sh "aws s3 ls s3://mamuu"
                sh "aws s3 cp target/hello-${BUILD_NUMBER}.war s3://mamuu/Dheeraj/${BRANCH_NAME}/${BUILD_NUMBER}"
            }
        }
    }
}
