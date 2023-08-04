def call(Map config=[:]) {
    def rawBody = libraryResource 'com/jenkins/api/jira/createsubtask.json'
    def binding = [
        key: "${config.key}",
        parentKey: "${config.parentKey}",
        summary: "${config.summary}",
        description: "${config.description}",
        issueTypeName: "${config.issueTypeName}"
    ]
    def render = renderTemplate(rawBody,binding)
    def response = sh('curl -D- -u $JIRA_CREDENTIALS -X POST --data "'+render+'" -H "Content-Type: application/json" $JIRA_URL/rest/api/2/issue')
    def jsonResponse = new groovy.json.JsonSlurper().parseText(response)
    def subtaskKey = jsonResponse.key
    echo "Subtask Key: ${subtaskKey}"
}
