def deployer(ENVIR){
    node {
	stage("Copy Artemis"){
		timestamps {
		ws {
			git  'https://github.com/fuchicorp/artemis.git'
		}
	}
	stage("Install Prerequisites"){
		timestamps {
			ws{
				sh "ssh centos@${ENVIR} sudo yum install epel-release -y"
				sh "ssh centos@${ENVIR} sudo yum install python-pip -y"
				sh "ssh centos@${ENVIR} sudo pip install Flask"
		}
	}
}
	stage("Copy Artemis"){
		timestamps {
		ws {
			sh "scp -r * centos@${ENVIR}:/tmp"
		}
	}
}
	stage("Run Artemis"){
		timestamps {
			ws {
				sh "ssh centos@${ENVIR} nohup python /tmp/artemis.py & "
		}
	}
}
	stage("Send slack notifications"){
		timestamps {
			ws {
				echo "Slack"
				//slackSend color: '#BADA55', message: 'Hello, World!'
			}
		}
	}
}
}
