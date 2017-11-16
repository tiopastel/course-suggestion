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
      parallel {
        stage('Application Dockerfile Setup') {
          steps {
            sh '''echo \'####### Seting up application Dockerfile ######\'
cd $WORKSPACE/target/tech.nerddash
rm -rf $JENKINS_HOME/Dockerfiles/Tomcat.8.5.23/ROOT/*
mv * $JENKINS_HOME/Dockerfiles/Tomcat.8.5.23/ROOT/
'''
          }
        }
        stage('Database Docker Setup') {
          steps {
            sh '''echo \'####### Seting up database Dockerfile ######\'
echo \'Nothing to be done :)\''''
          }
        }
      }
    }
    stage('Application Docker Build') {
      parallel {
        stage('Application Docker Build') {
          environment {
            APPLICATION_NAME = 'course-suggestion'
          }
          steps {
            sh '''echo \'####### Building application Dockerfile ######\'
cd $JENKINS_HOME/Dockerfiles/Tomcat.8.5.23
docker build -t $APPLICATION_NAME  .'''
          }
        }
        stage('Database Docker Build') {
          environment {
            DATABASE_NAME = 'mariadb'
          }
          steps {
            sh '''echo \'####### Building database Dockerfile ######\'
cd $JENKINS_HOME/Dockerfiles/Mariadb
docker build -t $DATABASE_NAME  .'''
          }
        }
      }
    }
  }
}