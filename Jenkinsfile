pipeline {
    agent any
    stages {
        stage ('Build Backend') {
            steps {
                bat 'mvn clean package -DskipTests=true'
            }
        }
           stage ('Unit Tests') {
            steps {
                bat 'mvn test'
            }
        } 
      stage ('Sonar Analysis') {
            environment {
                scannerHome = tool 'Sonar_Scanner'
            }
            steps {
                withSonarQubeEnv('SONAR_LOCAL') {
                    bat "${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=DeployBack -Dsonar.host.url=http://localhost:9000 -Dsonar.login=2a10df2ebe8c14bf9c658fce3252106b5cd38412 -Dsonar.java.binaries=target -Dsonar.coverage.exclusions=**/.mvn/**,**/src/test/**,**/model/**,**Application.java"
                }
            }
        }
      stage ('Quality Gate') {
            steps {
                sleep(20)
                timeout(time: 1, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }
}
