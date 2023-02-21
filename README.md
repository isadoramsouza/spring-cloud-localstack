# Spring Cloud AWS Project with LocalStack

With [LocalStack](https://localstack.cloud) it's possible to run AWS applications or Lambdas entirely on local machine without connecting to a remote cloud provider.

Installing LocalStack: https://github.com/localstack/localstack

Initializing LocalStack in project with parameters, secrets, buckets, queues and topics already created:

```
cd ./localstack
docker-compose up
bash localstack.sh
```

## AWS Services:

- <b>Parameter Store</b>
- <b>Secrets Manager</b>
- <b>S3</b>
- <b>SQS</b>
- <b>SNS</b>


### Running Spring Boot Application with localstack profile

```
./mvnw spring-boot:run -Dspring-boot.run.profiles=localstack
```

### Testing Application

- Parameter Store:

```
  curl --location --request GET 'http://localhost:8080/parameterstore/name'

  curl --location --request GET 'http://localhost:8080/parameterstore/days'
```

- Secrets Manageer:

``` 
curl --location --request GET 'http://localhost:8080/secretsmanager/configuration'
```

- S3:

``` 
curl --location --request POST 'http://localhost:8080/file'

curl --location --request GET 'http://localhost:8080/file?fileName={{fileName}}'

curl --location --request GET 'http://localhost:8080/file/{{fileName}}'
```

- SQS:

``` 
curl --location --request POST 'http://localhost:8080/sqs/send?message={{message}}'
```

- SNS:

``` 
curl --location --request POST 'http://localhost:8080/sns/send?message={{message}}'
```

#### Reference

https://thomasdacosta.com.br/
