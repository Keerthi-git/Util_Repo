def uploadArtifactory() {
  script {
    server= Artifactory.server prop.ARTIFACTORY_ID
            uploadSpec="""{
            "files":[{
                "pattern":"target/*.war",
                "target":"example-repo-local/${artifactId}/${version}.${buildNo}/"
                }]
                }"""
                server.upload(uploadSpec)
  }
}

def sendEmail() {
  emailtest(
    subject:'${DEFAUKT_SUBJECT}',
    body: '${DEFAULT_CONTENT}',
    to:prop.EMAIL_RECIPIENT
  );
  print 'mail sent'
}

def cleanWorkspace() {
  script {
    sh 'rm -rf ../'+jobName+'/*'
  }
  print 'cleaned workspace'
}

return this
