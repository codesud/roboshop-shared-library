def lintChecks() {
  sh '''
    echo installing jslint
    npm install jslint
    ~/node_modules/jslint/bin/jslint.js server.js || true
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
                    lintchecks()
                    }
                }
            }
        } //end of the stages
    }  // end of the pipeline
}  // end of function call

// defining the lintcheck function
 
