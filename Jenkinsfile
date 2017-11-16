pipeline {
  agent any
  stages {
    stage('Maven Install') {
      steps {
        sh 'mvn clean install'
      }
    }
    stage('Application Dockerfile Setup') {
      steps {
        sh '''echo $BUILD_TAG
echo $ITEM_FULL_NAME
echo $ITEM_ROOTDIR'''
      }
    }
  }
}