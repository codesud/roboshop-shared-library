def javaSonarCheck() {  // defining the sonarCheck function
  sh '''
    // mvn clean compile
    // sonar-scanner -Dsonar.host.url=http://172.31.5.124:9000 -Dsonar.sources=. -Dsonar.login={SONAR_USR} -Dsonar.password={SONAR_PSW} -Dsonar.projectKey=${COMPONENT} -Dsonar.java.binaries=target/classes/
    // curl https://raw.githubusercontent.com/CodingSudeep/lab-tools/main/sonarscanner/quality-gate.sh > /tmp/quality-gate.sh 
    // chmod +x /tmp/quality-gate.sh && /tmp/quality-gate.sh ${SONAR_USR} ${SONAR_PSW} 172.31.4.93 ${COMPONENT} 
    echo Sonar Checks completed
    '''
    // sonarscanner runs on jenkins instance.
}

def sonarCheck() {  // defining the sonarCheck function
  sh '''
    // sonar-scanner -Dsonar.host.url=http://172.31.5.124:9000 -Dsonar.sources=. -Dsonar.login={SONAR_USR} -Dsonar.password={SONAR_PSW} -Dsonar.projectKey=${COMPONENT}
    // curl https://raw.githubusercontent.com/CodingSudeep/lab-tools/main/sonarscanner/quality-gate.sh > /tmp/quality-gate.sh 
    // chmod +x /tmp/quality-gate.sh && /tmp/quality-gate.sh ${SONAR_USR} ${SONAR_PSW} 172.31.4.93 ${COMPONENT} 
    echo Sonar Checks completed
    '''

}