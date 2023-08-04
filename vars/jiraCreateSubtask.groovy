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
    def command = sh('curl -D- -u $JIRA_CREDENTIALS -X POST --data "'+render+'" -H "Content-Type: application/json" $JIRA_URL/rest/api/2/issue')
    def response = sh(returnStdout: true, script: command)
    echo response
    //def output = sh(script: curlCmd, returnStdout: true).trim()

    // Parse the JSON response to extract the key of the created subtask
    //def jsonResponse = readJSON text: output
    //def createdKey = jsonResponse.key

    //echo "Created Subtask Key: ${createdKey}"
}
