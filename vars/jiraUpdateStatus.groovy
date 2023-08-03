def call(Map config=[:]) {
    def rawBody = libraryResource 'com/jenkins/api/jira/updateStatus.json'
    def binding = [
        transitionId: "${config.transitionId}"
    ]
    def render = renderTemplate(rawBody,binding)
  sh('curl -D- -u $JIRA_CREDENTIALS -X POST --data \"$render\" -H "Content-Type: application/json" $JIRA_URL/rest/api/2/issue/POC-22/transitions')
}
