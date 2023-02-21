# Spring Cloud AWS Project with LocalStack

With [LocalStack](https://localstack.cloud) it's possible to run AWS applications or Lambdas entirely on local machine without connecting to a remote cloud provider.

Installing LocalStack: https://github.com/localstack/localstack

Initializing LocalStack in project with parameters, secrets, buckets and queues already created:

```
cd ./localstack
docker-compose up
bash localstack.sh
```

## AWS Services:

- <b>Parameter Store</b>
- <b>Secrets Manager</b>

### TODO

- <b>S3</b>
- <b>SQS</b>
- <b>SNS</b>

### Running Spring Boot Application with localstack profile

```
./mvnw spring-boot:run -Dspring-boot.run.profiles=localstack
```

### Testing Application

```
  curl --location --request GET 'http://localhost:8080/parameterstore/name'

  curl --location --request GET 'http://localhost:8080/parameterstore/days'

  curl --location --request GET 'http://localhost:8080/secretsmanager/configuration'

```

#### Reference

https://thomasdacosta.com.br/
