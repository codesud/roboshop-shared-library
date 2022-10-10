def lintChecks() {
  sh '''
    echo lint checks starting for ${COMPONENT}
    mvn checkstyle:check
    echo lint checks completed for ${COMPONENT}
    '''
}

def sonarCheck() {
  sh '''
    sonar-scanner -Dsonar.host.url=http://172.31.3.80:9000 -Dsonar.sources=. -Dsonar.login={SONAR_USR} -Dsonar.password={SONAR_PSW} -Dsonar.projectKey=shipping -Dsonar.java.binaries=target/classes/
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
        } //end of the stages
    }  // end of the pipeline
}  // end of function call

// defining the lintcheck function
 