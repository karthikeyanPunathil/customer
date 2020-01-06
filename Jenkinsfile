node{
  stage('SCM Checkout'){
  git 'https://github.com/Karthikeyanpk/customer'
  }
  stage('Compile-Package'){
  sh 'mvn install -Dmaven.test.skip=true'
  }
   stage('Build Docker Image'){
       sh 'sudo mvn package -Pprod verify jib:dockerBuild -Dmaven.test.skip=true'
   }
  stage('Push Docker Image'){
      withCredentials([string(credentialsId: 'docker-pwd', variable: 'dockerHubPwd')]) {
          sh "docker login -u byta3262 -p ${dockerHubPwd}"
}
    sh 'sudo docker tag customerappgateway byta3262/customerappgateway:latest'
    sh 'sudo docker push byta3262/customerappgateway'
  }
        
}
