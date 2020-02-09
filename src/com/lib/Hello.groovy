def runpipeline(ENVIR){
    echo "Hello"
    node {
        stage("sometime"){
        
        sh "ls -l" 
            ssh centos@"${ENVIR}"  ls /tmp
        }
    }
}
