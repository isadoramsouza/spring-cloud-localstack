@echo off

echo ### AWS Parameter Store
aws --endpoint http://localhost:4566 --profile localstack ssm put-parameter --name "/config/spring-cloud-localstack_localstack/name" --value "Isadora" --type String &
aws --endpoint http://localhost:4566 --profile localstack ssm put-parameter --name "/config/spring-cloud-localstack_localstack/days" --value "Monday,Wednesday,Saturday" --type StringList &
aws --endpoint http://localhost:4566 --profile localstack ssm put-parameter --name "/config/spring-cloud-localstack_localstack/sqsQueueName" --value "sqsQueue" --type String &
aws --endpoint http://localhost:4566 --profile localstack ssm put-parameter --name "/config/spring-cloud-localstack_localstack/snsNotificationTopicName" --value "snsTopic" --type String &

echo ### AWS Secrets Manager
aws --endpoint http://localhost:4566 --profile localstack secretsmanager create-secret --name /secret/spring-cloud-localstack_localstack --description "Secret Example" --secret-string "{\"value1\":\"Olá, Mundo\",\"value2\":\"Hello, World\",\"value3\":\"Hola, Mundo\"}" &
aws --endpoint http://localhost:4566 --profile localstack secretsmanager create-secret --name /secret/spring-cloud-localstack --description "Secret Example" --secret-string "{\"value1\":\"Olá, Mundo\",\"value2\":\"Hello, World\",\"value3\":\"Hola, Mundo\"}" &
aws --endpoint http://localhost:4566 --profile localstack secretsmanager create-secret --name /secret/application --description "Secret Example" --secret-string "{\"value1\":\"Olá, Mundo\",\"value2\":\"Hello, World\",\"value3\":\"Hola, Mundo\"}" &
aws --endpoint http://localhost:4566 --profile localstack secretsmanager create-secret --name /secret/application_localstack --description "Secret Example" --secret-string "{\"value1\":\"Olá, Mundo\",\"value2\":\"Hello, World\",\"value3\":\"Hola, Mundo\"}" &

echo ### Bucket no S3
aws --endpoint http://localhost:4566 --profile localstack s3 mb s3://mybucket &

echo ### SQS Queue
aws --endpoint http://localhost:4566 --profile localstack sqs create-queue --queue-name sqsQueue &

echo ### SNS Topic and Subscribe
aws --endpoint http://localhost:4566 --profile localstack sns create-topic --name snsTopic &
aws --endpoint http://localhost:4566 --profile localstack sns subscribe --topic-arn arn:aws:sns:us-east-1:000000000000:snsTopic --protocol sqs --notification-endpoint arn:aws:sqs:us-east-1:000000000000:sqsQueue
