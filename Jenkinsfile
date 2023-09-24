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
    stage ('Deploy Backend') {
            steps {
                deploy adapters: [tomcat8(credentialsId: 'login', path: '', url: 'http://localhost:8001')], contextPath: 'tasks-backend', war: 'target/tasks-backend.war'
            }
        }
     stage ('API Test') {
            steps {
                dir('api-test') {
                    git  url: 'https://github.com/julioMoudatsos/wcApiTest'
                    bat 'mvn test'
                }
            }
        }
     stage ('Deploy Frontend') {
            steps {
                dir('frontend') {
                    git  url: 'https://github.com/julioMoudatsos/tasks-frontend'
                    bat 'mvn clean package'
                    deploy adapters: [tomcat8(credentialsId: 'login', path: '', url: 'http://localhost:8001')], contextPath: 'tasks', war: 'target/tasks.war'
                }
            }
        }
        stage ('Functional Test') {
            steps {
                dir('functional-test') {
                    git  url: 'https://github.com/julioMoudatsos/wcFuncionalTest'
                    bat 'mvn test'
                }
            }
        }
        stage ('Deploy Prod') {
            steps {
                bat 'docker-compose build'
                bat 'docker-compose up -d'
            }
        }
        
    }
}
