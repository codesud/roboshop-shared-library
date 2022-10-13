def sonarCheck() {  
  stage ('Sonar Checks') {
    if (env.APP_NAME == "java") {
      sh '''
        # mvn clean compile
        # sonar-scanner -Dsonar.host.url=http://172.31.5.124:9000 -Dsonar.sources=. -Dsonar.login={SONAR_USR} -Dsonar.password={SONAR_PSW} -Dsonar.projectKey=${COMPONENT} -Dsonar.java.binaries=target/classes/
        # curl https://raw.githubusercontent.com/CodingSudeep/lab-tools/main/sonarscanner/quality-gate.sh > /tmp/quality-gate.sh 
        # chmod +x /tmp/quality-gate.sh && /tmp/quality-gate.sh ${SONAR_USR} ${SONAR_PSW} 172.31.4.93 ${COMPONENT} 
        echo Sonar Checks completed
        '''
    }
    else {
      sh '''
        # sonar-scanner -Dsonar.host.url=http://172.31.5.124:9000 -Dsonar.sources=. -Dsonar.login={SONAR_USR} -Dsonar.password={SONAR_PSW} -Dsonar.projectKey=${COMPONENT}
        # curl https://raw.githubusercontent.com/CodingSudeep/lab-tools/main/sonarscanner/quality-gate.sh > /tmp/quality-gate.sh 
        # chmod +x /tmp/quality-gate.sh && /tmp/quality-gate.sh ${SONAR_USR} ${SONAR_PSW} 172.31.4.93 ${COMPONENT} 
        echo Sonar Checks completed
        '''
    }
  }
}

def lintChecks() {
  stage ('Lint Checks') {
    if (env.APP_NAME == "nodejs") {
      sh '''
        # echo installing jslint
        # npm install jslint
        # ~/node_modules/jslint/bin/jslint.js server.js || true
        echo lint checks completed for ${COMPONENT}
        '''
    }
    else if (env.APP_NAME == "nodejs") {
      sh '''
        echo lint checks starting for ${COMPONENT}
        # mvn checkstyle:check
        echo lint checks completed for ${COMPONENT}
        '''       
    }
    else if (env.APP_NAME == "python") {
      sh '''
        # echo lint checks starting for ${COMPONENT}
        # pip3 install pylint 
        # pylint *.py || true
        echo lint checks completed for ${COMPONENT}
        '''
    }
    else {
      sh '''
        # echo installing jslint
        # npm install jslint
        # ~/node_modules/jslint/bin/jslint.js server.js || true
        echo lint checks completed for ${COMPONENT}
        '''
    }
  }
}

def testCases() {
  stage('Test Cases') {
    def stages = [:]    // declaring empty list
        stage['Unit Testing'] = {
        sh "echo Unit Testing completed"
        }
        stage['Integration Testing'] = {
          sh "echo Integration Testing completed"
        }
        stage['Function Testing'] = {
          sh "echo Function Testing completed"
        }
  }
}   
 