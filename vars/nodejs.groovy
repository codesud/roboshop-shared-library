env.APP_NAME == "nodejs"
def call() {
    node {
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
//         NEXUS = credentials('nexus')
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
//                 stage('Unit Testing') {
//                     steps {
//                         // mvn test or npm test
//                         sh "echo Unit Testing completed"
//                         }
//                     }
//                 stage('Integration Testing') {
//                     steps {
//                         // mvn verify or npm verify
//                         sh "echo Integration Testing completed"
//                         }
//                     }
//                 stage('Function Testing') {
//                     steps {
//                         sh "echo Function Testing completed"
//                         }
//                     }
//                 }
//             }
// // Checking the presence of Artifact in Nexus
//         stage('Checking the release') {
//             when {
//                 expression { env.TAG_NAME != null }
//             }
//             steps {
//                 script {
//                 env.UPLOAD_STATUS=sh(returnStdout: true, script: "curl http://172.31.4.108:8081/service/rest/repository/browse/${COMPONENT}/ | grep ${COMPONENT}-${TAG_NAME}.zip || true")
//                 print UPLOAD_STATUS
//                 }
//             }
//         }
// // Preapring the artifact should happen only of it is does not exist in Nexus
//         stage('Prepare Artifacts') {
//             when {
//                 expression { env.TAG_NAME != null }
//                 expression { env.UPLOAD_STATUS =="" }
//                 }  
//             steps {
//                 sh "npm install" // generates the nodes_modules 
//                 sh "zip -r ${COMPONENT}-${TAG_NAME}.zip node_modules server.js"
//                 sh "echo Artifacts Preparation Completed.....................!!!"
//                 }
//             }
//         stage ('Uploading Artifacts') {
//             when {
//                 expression { env.TAG_NAME != null }
//                 expression { env.UPLOAD_STATUS =="" }
//                 }  
//             steps {
//                 sh 'curl -f -v -u ${NEXUS_USR}:${NEXUS_PSW} --upload-file ${COMPONENT}-${TAG_NAME}.zip http://172.31.4.108:8081/repository/${COMPONENT}/${COMPONENT}-${TAG_NAME}.zip'
//                 // curl returns failure when failed when you use -f  
//                }
//             }
//         } //end of the stages
//     }  // end of the pipeline
// }  // end of function call
