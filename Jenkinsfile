pipeline {
  agent any

  environment {
    PROJECT_ID = 'gcp-demo-96640'
    REGION = 'asia-east1'
    SERVICE_NAME = 'spring-adapter'
    REPO = 'quickstart-docker-repo'
    IMAGE_URI = "${REGION}-docker.pkg.dev/${PROJECT_ID}/${REPO}/${SERVICE_NAME}"
    GCP_SA_KEY = credentials('gcp-service-account')
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Build JAR') {
      steps {
        sh 'mvn clean package -DskipTests'
      }
    }

    stage('Build Docker Image') {
      steps {
        sh "docker build -t ${IMAGE_URI}:latest ."
      }
    }

    stage('Authenticate to GCP') {
      steps {
        writeFile file: 'gcp-key.json', text: "${GCP_SA_KEY}"
        sh '''
          gcloud auth activate-service-account --key-file=gcp-key.json
          gcloud config set project ${PROJECT_ID}
        '''
      }
    }

    stage('Push Docker Image to Artifact Registry') {
      steps {
        sh '''
          gcloud auth configure-docker ${REGION}-docker.pkg.dev --quiet
          docker push ${IMAGE_URI}:latest
        '''
      }
    }

    stage('Deploy to Cloud Run') {
      steps {
        sh '''
          gcloud run deploy ${SERVICE_NAME}             --image=${IMAGE_URI}:latest             --region=${REGION}             --platform=managed             --allow-unauthenticated=false             --ingress=all
        '''
      }
    }
  }

  post {
    always {
      sh 'rm -f gcp-key.json'
    }
  }
}