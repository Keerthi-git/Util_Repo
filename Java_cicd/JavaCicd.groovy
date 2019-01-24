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
  
  stage ('Docker deployment') {
    sh 'docker stop $(docker ps -a -q --filter ancestor=tomcat:8.0)'
	sh 'docker build -t tomcat -f Util_Repo/docker/dockerfile .'
	sh 'docker run --rm -d -p 8084:8080 tomcat:8.0'
	  
  }
}
return this
