def runpipeline(ENVIR){
    echo "Hello"
    node {
        stage("sometime"){
        
            sh "ssh centos@${ENVIR} ls /tmp"
        }
    }
}
