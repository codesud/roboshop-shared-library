def call() {
pipeline {
    agent any
    stages {
        // This should run for every commit of feature branch
        stage('Lint checks') {
            steps {
                script {
                    nodejs.lintcheck(COMPONENT)
                    }
                }
            }
        } //end of the stages
    }  // end of the pipeline
}  // end of function call

// defining the lintcheck function
def lintcheck(COMPONENT) {     // Declaring a function
    echo "lint checks started for ${COMPONENT}" 
}

info("Hai", "twitter.com")             // Calling a function

