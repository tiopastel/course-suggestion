pipeline {
  agent any
  stages {
    stage('Maven Tests') {
      steps {
        sh 'mvn clean install'
        sh 'echo $WORKSPACE'
      }
    }
    stage('Docker Setup') {
      agent any
      steps {
        echo 'Loggin on Docker Hub ......'
        sh 'docker login -u tiopastel -p parafi123'
        echo 'Seting up database Dockerfile'
        sh 'touch $JENKINS_HOME/Dockerfiles/Mariadb/my.cnf'
        sh 'touch $JENKINS_HOME/Dockerfiles/Mariadb/start.sh'
        echo 'Seting up application Dockerfile'
        sh 'cd $WORKSPACE/target/tech.nerddash'
        sh '''rm -rf $JENKINS_HOME/Dockerfiles/Tomcat/ROOT/*
'''
        sh 'mv * $JENKINS_HOME/Dockerfiles/Tomcat/ROOT/'
        sh 'touch $JENKINS_HOME/Dockerfiles/Tomcat/persistence.xml'
        sh 'touch $JENKINS_HOME/Dockerfiles/Tomcat/web.xml'
      }
    }
  }
  environment {
    DOCKERHUB_USERNAME = 'parafi123'
    DATABASE_NAME = 'mariadb'
    APPLICATION_NAME = 'course-suggestion'
  }
}