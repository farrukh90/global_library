def runpipeline(){
    echo "Hello"
    node {
        stage("sometime"){
        
        sh "ls -l" 
        }
    }
}
