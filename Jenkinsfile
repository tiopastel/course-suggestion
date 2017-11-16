pipeline {
  agent any
  stages {
    stage('Maven Install') {
      steps {
        sh '''mvn clean install
'''
      }
    }
    stage('Application Dockerfile Setup') {
      steps {
        sh '''echo \'####### Seting up application Dockerfile ######\'
cd $WORKSPACE/target/tech.nerddash
rm -rf $JENKINS_HOME/Dockerfiles/Tomcat.8.5.23/ROOT/*
mv * $JENKINS_HOME/Dockerfiles/Tomcat.8.5.23/ROOT/
'''
      }
    }
    stage('Application Docker Build') {
      steps {
        sh '''echo \'####### Building application Dockerfile ######\'
cd $JENKINS_HOME/Dockerfiles/Tomcat.8.5.23
docker build -t nerddash/course-suggestion .'''
      }
    }
  }
}