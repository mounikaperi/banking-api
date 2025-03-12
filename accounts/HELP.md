Maven commands:
1. Compile your code and generate a jar
    **mvn clean install**
2. To start and run the application using maven-plugin in pom.xml: 
   **mvn spring-boot:run**
3. To start and run the application using java command: 
   **java -jar target/ba-accounts-0.0.1-SNAPSHOT.jar**
4. To build the Dockerfile: docker build "location where docker file is present" -t (docker-account-username/application-name:tagnameversionname) Note: (name your dockerfile as Dockerfile D caps f small)
   **docker build . -t mounikaperi/ba-accounts:a0** 
   Results: mounikaperi/ba-accounts   a0        0415a6b46617   40 seconds ago   504MB
5. To list all the images in your local
   **docker images** 
6. To inspect a specific image
   **docker inspect image (imageId)**
7. To run a docker container from docker image 
    **docker run -p 8080:8080 mounikaperi/ba-accounts:a0**
    -p indicates port
    first 8080: indicates the port that docker exposes the container to the outside of docker network at port 8080
    second 8080: mapping to our port
    mounikaperi/ba-accounts:a0 - image name
8. When you run the container in above way you cannot run further any commands. you have to detach the container and let it run in background
   **docker run -d -p 8080:8080 mounikaperi/ba-accounts:a0**
9. To know the containers that are running inside the docker server
    **docker ps**
10. To know all the stopped containers
    **docker ps -a**