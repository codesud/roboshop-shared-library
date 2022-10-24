def call() {
    node {
        sh "rm -rf *"
        git branch: 'main', url: "https://github.com/CodingSudeep/${COMPONENT}"
        env.APP_TYPE == "nodejs"
        
        stage('Docker Build')
            sh "docker build ."
        common.lintChecks()
        common.sonarCheck()
        common.testCases()
        if(env.TAG_NAME != null) {
        common.artifacts()
        }
    }
}