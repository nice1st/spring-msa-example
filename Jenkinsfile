pipeline {
    agent any
    options { skipDefaultCheckout(false) }
    stages {
        stage('Build') {
            tools {
                   jdk "openjdk-14"
            }
            steps {
                dir("cctv") {
                    sh 'chmod 777 gradlew'
                    sh './gradlew build'
                }
            }
        }
        stage('Docker build') {
            steps {
                dir("subproject/msa-news") {
                    // 이미지 뒤에 :버전(:날짜) 붙여야 됨
                    sh 'docker build -t app-api-msa-news-image .'
                }
                dir("subproject/msa-review") {
                    sh 'docker build -t app-api-msa-review-image .'
                }
            }
        }
        stage('Docker run') {
            steps {
                script {
                    sh """
                        /bin/bash -c $/"docker ps -f name=app-api-msa-news-container -q | xargs --no-run-if-empty docker container stop"/$
                    """
                    sh """
                        /bin/bash -c $/"docker container ls -a -fname=name=app-api-msa-news-container -q | xargs -r docker container rm"/$
                    """
                    sh """
                        /bin/bash -c $/"docker ps -f name=app-api-msa-review-container -q | xargs --no-run-if-empty docker container stop"/$
                    """
                    sh """
                        /bin/bash -c $/"docker container ls -a -fname=app-api-msa-review-container -q | xargs -r docker container rm"/$
                    """

                    sh """
                        /bin/bash -c $/"docker images | grep '<none>' | awk '{ print \$3; }' | xargs -r docker rmi"/$
                    """

                    sh "docker run -d --name name=app-api-msa-news-container -p 18005:18005 name=app-api-msa-news-image"
                    sh "docker run -d --name app-api-msa-review-container -p 18010:18010 app-api-msa-review-image"
                }
            }
        }
    }
}
