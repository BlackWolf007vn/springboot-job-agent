# spring-adapter CI/CD with Jenkins

## Deploy to GCP Cloud Run via Jenkins in 5 Steps

1. Push your code with Jenkinsfile to Git repository
2. Setup Jenkins with Docker, Maven, Java 21, and gcloud CLI
3. Add service account JSON as Jenkins Secret with ID `gcp-service-account`
4. Run Jenkins pipeline to build, push and deploy
5. Check your Cloud Run service at https://console.cloud.google.com/run