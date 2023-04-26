DOCKER_STACK_NAME = workshop
DOCKER_PATH = docker
DOCKER_COMPOSE_PATH = ${DOCKER_PATH}/docker-compose.yml
DOCKER_COMPOSE_COMMAND = docker-compose \
	-p ${DOCKER_STACK_NAME} \
	-f ${DOCKER_COMPOSE_PATH}

docker-up:
	${DOCKER_COMPOSE_COMMAND} up -d

docker-down:
	${DOCKER_COMPOSE_COMMAND} down || true

docker-start:
	${DOCKER_COMPOSE_COMMAND} start || true

docker-stop:
	${DOCKER_COMPOSE_COMMAND} stop || true

build-vehicles:
	cd vehicles && ./mvnw clean package

run-vehicles:
	cd vehicles && ./mvnw clean spring-boot:run -Dspring-boot.run.profiles=local

build-dealers:
	cd dealers && ./mvnw clean package

run-dealers:
	cd dealers && ./mvnw clean spring-boot:run -Dspring-boot.run.profiles=local

build-store-java:
	cd store-java && ./mvnw clean package

run-store-java:
	cd store-java && ./mvnw clean spring-boot:run -Dspring-boot.run.profiles=local

build-store-kotlin:
	cd store-kotlin && ./mvnw clean package

run-store-kotlin:
	cd store-kotlin && ./mvnw clean spring-boot:run -Dspring-boot.run.profiles=local