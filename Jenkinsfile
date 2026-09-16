pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package'
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                    export JENKINS_NODE_COOKIE=dontKillMe
                    nohup java -jar target/TodoProject-1.0-SNAPSHOT.jar > app.log 2>&1 &
                '''
            }
        }
    }

    post {
        success {
            emailext(
                subject: "SUCCESS: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """Todo Maven CI/CD Pipeline completed successfully.

                Build: ${env.BUILD_URL}

                Application deployed on Jenkins server.
                """,
                to: 'kudirillapravallika.23.cse@anits.edu.in'
            )
        }

        failure {
            emailext(
                subject: "FAILED: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """Todo Maven CI/CD Pipeline failed.

                Build: ${env.BUILD_URL}
                """,
                to: 'kudirillapravallika.23.cse@anits.edu.in'
            )
        }
    }
}