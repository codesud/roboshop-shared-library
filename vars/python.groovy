def call() {
    node {
        git branch: 'main', url: "https://github.com/CodingSudeep/${COMPONENT}"
        env.APP_TYP == "python"
        common.lintChecks()
        common.sonarCheck()
        common.testCases()
        if(env.TAG_NAME != null) {
        common.artifacts()
        }
    }
}

// def call() {    // call is the default function which will be called by default
// pipeline {
//     agent any
//     environment { 
//         SONAR = credentials('sonar')
//     }
//     stages {
//         // This should run for every commit of feature branch
//         stage('Lint checks') {
//             steps {
//                 script {
//                     lintChecks()
//                     }
//                 }
//             }
//         stage('Sonar Code Quality Check') {
//             steps {
//                 script {
//                     common.sonarCheck()
//                     }
//                 }
//             }
//         stage('Test Cases') {
//             parallel {
//                 stage('Unit Testing')
//                     steps {
//                         // mvn test or npm test
//                         sh "echo Unit Testing completed"
//                     }
//                 stage('Integration Testing')
//                     steps {
//                         // mvn verify or npm verify
//                         sh "echo Integration Testing completed"
//                     }
//                 stage('Function Testing')
//                     steps {
//                         sh "echo Function Testing completed"
//                     }
//                 }
//             }
//         stage {
//             steps {
//                 sh "echo Doing build"
//                 }
//             }
//         } //end of the stages
//     }  // end of the pipeline
// }  // end of function call
