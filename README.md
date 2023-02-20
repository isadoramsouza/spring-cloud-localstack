# Spring Cloud AWS Project with LocalStack

With [LocalStack](https://localstack.cloud) it's possible to run AWS applications or Lambdas entirely on local machine without connecting to a remote cloud provider.

Installing localStack: https://github.com/localstack/localstack

Initializing LocalStack in project:

```
cd ./localstack
docker-compose up
```



## AWS Services:

- <b>Parameter Store</b>
    - Add a new parameters:

      ```
      aws --endpoint http://localhost:4566 --profile localstack ssm put-parameter --name "/config/spring-cloud-localstack_localstack/name" --value "Isadora" --type String
      
      aws --endpoint http://localhost:4566 --profile localstack ssm put-parameter --name "/config/spring-cloud-localstack_localstack/days" --value "Monday,Wednesday,Saturday" --type StringList
      ```


### Run Spring Boot Application with localstack profile
  ```
  ./mvnw spring-boot:run -Dspring-boot.run.profiles=localstack
  ```
### Test Application
  ```
    curl --location --request GET 'http://localhost:8080/parameterstore/name'
  
    curl --location --request GET 'http://localhost:8080/parameterstore/days'
  ```
