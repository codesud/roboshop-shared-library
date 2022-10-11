def lintChecks() {
  sh '''
    echo lint checks starting for ${COMPONENT}
    # mvn checkstyle:check
    echo lint checks completed for ${COMPONENT}
    '''
}

 

def call() {    // call is the default function which will be called by default
pipeline {
    agent any
        environment { 
        SONAR = credentials('sonar')
        }
    stages {
        // This should run for every commit of feature branch
        stage('Lint checks') {
            steps {
                script {
                    lintChecks()
                    }
                }
            }
        stage('Sonar Code Quality Check') {
            steps {
                script {
                    common.javaSonarCheck()
                    }
                }
            }
        stage('Test Cases') {
            parallel {
                stage('Unit Testing')
                    steps {
                        // mvn test or npm test
                        sh "echo Unit Testing completed"
                    }
                stage('Integration Testing')
                    steps {
                        // mvn verify or npm verify
                        sh "echo Integration Testing completed"
                    }
                stage('Function Testing')
                    steps {
                        sh "echo Function Testing completed"
                    }
                }
            }
        stage {
            steps {
                sh "echo Doing build"
                }
            }
        } //end of the stages
    }  // end of the pipeline
}  // end of function call
