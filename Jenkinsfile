pipeline {
  agent any
  stages {
    stage('Maven Install') {
      steps {
        sh '''mvn clean install
'''
      }
    }
    stage('Dockerfile Setup') {
      steps {
        sh '''echo \'Loggin on Docker Hub ......\'
docker login -u tiopastel -p parafi123

echo \'####### Seting up database Dockerfile ######\'
touch $JENKINS_HOME/Dockerfiles/Mariadb/database/conf/my.cnf

echo \'####### Seting up application Dockerfile ######\'
cd $WORKSPACE/target/tech.nerddash
rm -rf $JENKINS_HOME/Dockerfiles/Tomcat.8.5.23/ROOT/*
mv * $JENKINS_HOME/Dockerfiles/Tomcat.8.5.23/ROOT/
'''
      }
    }
    stage('Application Docker Build') {
      parallel {
        stage('Application Docker Build') {
          steps {
            sh '''echo \'####### Building application Dockerfile ######\'
cd $JENKINS_HOME/Dockerfiles/Tomcat.8.5.23
docker build -t $DOCKER_USERNAME/$APPLICATION_NAME  .'''
          }
        }
        stage('Database Docker Build') {
          steps {
            sh '''echo \'####### Building database Dockerfile ######\'
cd $JENKINS_HOME/Dockerfiles/Mariadb
docker build -t $DOCKER_USERNAME/$DATABASE_NAME  .'''
          }
        }
      }
    }
    stage('Pushing Application Docker Image') {
      parallel {
        stage('Pushing Application Docker Image') {
          steps {
            sh 'docker push $DOCKER_USERNAME/$APPLICATION_NAME'
          }
        }
        stage('Pushing Database Docker Image') {
          steps {
            sh 'docker push $DOCKER_USERNAME/$DATABASE_NAME'
          }
        }
      }
    }
  }
  environment {
    DOCKER_USERNAME = 'tiopastel'
    DOCKER_PASSWORD = 'parafi123'
    DATABASE_NAME = 'mariadb'
    APPLICATION_NAME = 'course-suggestion'
    EMAIL_ADDRESS = 'arantesbarcelos@gmail.com'
  }
  post {
    failure {
      mail(to: 'arantesbarcelos@gmail.com', subject: "Failed Pipeline: ${currentBuild.fullDisplayName}", body: "Something is wrong with ${env.BUILD_URL}")
      
    }
    
     
    success {
      mail(to: 'arantesbarcelos@gmail.com', subject: "Successed Pipeline: ${currentBuild.fullDisplayName}", body: "${env.BUILD_URL} was successefully build.")
      
    }
    
  }
}
