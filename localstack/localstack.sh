@echo off

echo ### AWS Parameter Store
aws --endpoint http://localhost:4566 --profile localstack ssm put-parameter --name "/config/spring-cloud-localstack_localstack/name" --value "Isadora" --type String &
aws --endpoint http://localhost:4566 --profile localstack ssm put-parameter --name "/config/spring-cloud-localstack_localstack/days" --value "Monday,Wednesday,Saturday" --type StringList &

echo ### AWS Secrets Manager
aws --endpoint http://localhost:4566 --profile localstack secretsmanager create-secret --name /secret/spring-cloud-localstack_localstack --description "Secret Example" --secret-string "{\"value1\":\"Olá, Mundo\",\"value2\":\"Hello, World\",\"value3\":\"Hola, Mundo\"}" &
aws --endpoint http://localhost:4566 --profile localstack secretsmanager create-secret --name /secret/spring-cloud-localstack --description "Secret Example" --secret-string "{\"value1\":\"Olá, Mundo\",\"value2\":\"Hello, World\",\"value3\":\"Hola, Mundo\"}" &
aws --endpoint http://localhost:4566 --profile localstack secretsmanager create-secret --name /secret/application --description "Secret Example" --secret-string "{\"value1\":\"Olá, Mundo\",\"value2\":\"Hello, World\",\"value3\":\"Hola, Mundo\"}" &
aws --endpoint http://localhost:4566 --profile localstack secretsmanager create-secret --name /secret/application_localstack --description "Secret Example" --secret-string "{\"value1\":\"Olá, Mundo\",\"value2\":\"Hello, World\",\"value3\":\"Hola, Mundo\"}"
