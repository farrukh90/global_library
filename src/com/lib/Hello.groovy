def runpipeline(message){
    echo "Hello"
    node {
        stage("sometime"){
        
        sh "ls -l" 
            echo "${message}"
        }
    }
}
