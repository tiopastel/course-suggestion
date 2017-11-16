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
echo $WORKSPACE
cd $WORKSPACE/target/tech.nerddash
echo $PWD'''
      }
    }
  }
}