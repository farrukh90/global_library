def deployer(ENVIR, REPO, APP_VERSION){
	properties([
		parameters([
			choice(choices: ['dev1.acirrustech.com', 'qa1.acirrustech.com', 'stage1.acirrustech.com', 'prod1.acirrustech.com'], description: 'Please enter an environment', name: 'ENVIR'),
			string(defaultValue: 'https://github.com/farrukh90/artemis.git', description: 'https://github.com/farrukh90/artemis.git', name: 'REPO', trim: false)		
			])
		])
	node {
		stage("Copy Artemis"){
			timestamps {
				ws {
					checkout([$class: 'GitSCM', branches: [[name: "*/${APP_VERSION}"]], extensions: [], userRemoteConfigs: [[url: "${REPO}"]]])
				}
			}
		}
		// stage("Install Prerequisites"){
		// 	timestamps {
		// 		ws{
		// 			sh "ssh centos@${ENVIR} sudo yum install epel-release -y"
		// 			sh "ssh centos@${ENVIR} sudo yum install python-pip -y"
		// 			sh "ssh centos@${ENVIR} sudo pip install Flask"
		// 		}
		// 	}
		// }
		// stage("Copy Artemis"){
		// 	timestamps {
		// 		ws {
		// 			sh "scp -r * centos@${ENVIR}:/tmp"
		// 		}
		// 	}
		// }
		// stage("Run Artemis"){
		// 	timestamps {
		// 			ws {
		// 				sh "ssh centos@${ENVIR} nohup python /tmp/artemis.py & "
		// 		}
		// 	}
		// }
		// stage("Send slack notifications"){
		// 	timestamps {
		// 		ws {
		// 			echo "Slack"
		// 			//slackSend color: '#BADA55', message: 'Hello, World!'
		// 		}
		// 	}
		// }
	}
}
