def deploy(){
    node{
      currentBuild.result = 'SUCCESS'
      stage("Git Pull"){
          checkout([$class: 'GitSCM', branches: [[name: 'master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/farrukh90/Flaskex.git']]])
      }
      stage("Build"){
          sh "docker build -t farrukhsadykov/flaskex:${env.BUILD_NUMBER} ."
      }
      stage("Docker Login"){
          withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]) {
              sh """
                  docker login -u ${USERNAME} -p ${PASSWORD} 
                  docker push farrukhsadykov/flaskex:"${env.BUILD_NUMBER}"
              """
          }   
      }
      stage("test"){
          echo "hello"
      }
  }
}




//To run this, create global library
//in the jenkinsfile

//@Library('global_library') _
//def docker = new com.lib.docker()
//   docker.deploy()



