pipeline {
  agent any
  stages {
    stage('Maven Tests') {
      steps {
        sh 'mvn clean install'
        sh 'ls $WORKSPACE'
      }
    }
    stage('Docker Login') {
      steps {
        sh 'docker login -u $DOCKERHUB_USERNAME -p $DOCKERHUB_PASSWORD'
      }
    }
  }
  environment {
    DOCKERHUB_USERNAME = 'parafi123'
    DATABASE_NAME = 'mariadb'
    APPLICATION_NAME = 'course-suggestion'
  }
}