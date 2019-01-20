def execute () {
  stage ('checkout') {
        git url:prop.URL
            branch:prop.BRANCH
            pom=readMavenPom file:prop.POM_FILE
            artifactId=pom.artifactId
            version=pom.version
            print 'checkout success'
   }
}
return this
