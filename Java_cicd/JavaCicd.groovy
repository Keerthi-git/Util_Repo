def execute () {
  stage ('checkout') {
        git url:prop.URL
            branch:prop.BRANCH
            pom=readMavenPom file:prop.POM_FILE
            artifactId=pom.artifactId
            version=pom.version
            print 'checkout success'
   }
  
  stage('Sonar scan and maven Build'){
    sh prop.HOST+' '+prop.LOGIN
    print'sonar success'
    sh 'mvn clean install'
  }
  
  stage('artifactry upload and tomcat deployment') {
            common.uploadArtifactory();
            sh prop.WAR_FILE+' '+prop.DEPLOY_LOCATION 
         }
}
return this
