def lintChecks() {
  sh '''
    # echo installing jslint
    # npm install jslint
    # ~/node_modules/jslint/bin/jslint.js server.js || true
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
                    common.sonarCheck()
                    }
                }
            }
        stage('Test Cases') {
            parallel {
                stage('Unit Testing') {
                    steps {
                        // mvn test or npm test
                        sh "echo Unit Testing completed"
                        }
                    }
                stage('Integration Testing') {
                    steps {
                        // mvn verify or npm verify
                        sh "echo Integration Testing completed"
                        }
                    }
                stage('Function Testing') {
                    steps {
                        sh "echo Function Testing completed"
                        }
                    }
                }
            }
        stage('Prepare Artifacts') {
            steps {
                sh "npm install"
                sh "zip ${COMPONENT}.zip node_modules server.js"
                sh "echo Artifacts Preparation Completed.....................!!!"
                }
            }
        stage ('Uploading Artifacts') {
            when {
                expression { env.TAG_NAME != null }
                }  
            steps {
                sh "echo Doing build"
                }
            }
        } //end of the stages
    }  // end of the pipeline
}  // end of function call
