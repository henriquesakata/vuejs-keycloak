# vuejs-keycloak

## Required Technologies

- Java 8
- NodeJS
- Docker
- Maven

## Setup

### Install Keycloak on Docker

To start Keycloak on Docker simply run 

```sh
docker run -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin -p 9080:8080 jboss/keycloak
```

This will expose Keycloak on localhost:9080. If you want to expose Keycloak on a different port replace the first 9080 with the port you want to expose it on. 

You should now have Keycloak server up and running. To check that it's working open http://DOCKER-IP:9080/auth or http://localhost:9080/auth if you exposed it on localhost with -p. Then click on Admin Console the username is admin and password is admin. 

Create new Realm and upload rockt-realm.json, then click on Create button.

### Build project

```sh
mvn clean install -P frontend
```

## License

[MIT](http://opensource.org/licenses/MIT)

Copyright (c) 2017-present, Henrique Sakata

