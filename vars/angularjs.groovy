def call() {
    node {
        git branch: 'main', url: "https://github.com/CodingSudeep/${COMPONENT}"
        env.APP_TYPE = "nginx"
        common.lintChecks()
        common.sonarCheck()
        common.testCases()
        if(env.TAG_NAME != null) {
        common.artifacts()
        }
    }
}
