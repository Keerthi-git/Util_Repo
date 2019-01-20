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

def cleanWorkspace() {
  script {
    sh 'rm -rf ../'+jobName+'/*'
  }
  print 'cleaned workspace'
}

return this
