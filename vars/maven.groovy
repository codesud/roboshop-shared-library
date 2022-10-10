def lintChecks() {
  sh '''


    echo lint checks completed for ${COMPONENT}
    '''
}

def call() {    // call is the default function which will be called by default
pipeline {
    agent any
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
 