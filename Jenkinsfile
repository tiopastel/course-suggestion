pipeline {
  agent any
  stages {
    stage('Application Dockerfile Setup') {
      steps {
        sh '''echo $BUILD_TAG
echo $WORKSPACE
cd $WORKSPACE/target/tech.nerddash
echo $PWD
mv * $JENKINS_HOME/Dockerfiles/Tomcat.8.5.23/ROOT/
docker build -t nerddash/$JOB_NAME .'''
      }
    }
  }
}