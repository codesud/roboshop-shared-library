def info(message, URL) {     // Declaring a function
    echo "INFO: ${message} , url value is ${URL}" 
}

info("Hai", "twitter.com")             // Calling a function

def call() {
pipeline {
    agent any
    stages {
        // This should run for every commit of feature branch
        stage('Lint checks') {
            steps {
                script {
                    nodejs.lintcheck()
                    }
                }
            }
        } //end of the stages
    }  // end of the pipeline
}  // end of function call