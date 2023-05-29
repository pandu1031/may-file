//Declarative pipeline
pipeline{
    agent any
    parameters {
        string(name:'BRANCH_NAME', defaultValue: 'master', description: 'Enter the source branch name ')
         string(name: 'BUILD_PIPE', defaultValue: '', description: 'Enter the pipeline branch name')
         string(name: 'SERVER_IP', defaultValue: '', description: 'Enter the server ip')
    }
    stages{
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
        stage("Deploy"){
            steps{
                println "Deploying artifacts from jenkins server to tomcat "                
                //sh "scp -i /tmp/mamu1031.pem /tmp/tomcatintsallation.sh ec2-user@${SERVER_IP}:/tmp/"
               //sh "ssh -i /tmp/mamu1031.pem ec2-user@${SERVER_IP} \"bash /tmp/tomcatintsallation.sh\""
               sh "scp -i /tmp/mamu1031.pem target/hello-${BUILD_NUMBER}.war ec2-user@${SERVER_IP}:/var/lib/tomcat/webapps/"
            }
        }
    }
}