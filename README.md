# SpringBoot REST API JSON App and XML Convertor with Networking Example

## Project Overview
* This project demos about the usage of Docker Networking with Springboot Applicaitons, This Project has 2 parts, one springboot application makes a rest call to `https://swapi.dev/api/` to get the JSON body and we have another Sprinboot application which make a REST call to the middle layer sprinboot and converts the response JSON to trimmed version XML response 
* Also This project demos the usage of creation of networks for the purpose of connectivity between apps
  
### Docker Network Terms
* **Docker Network**: Docker Networking is to connect the docker container to each other and outside world so they can communicate with each other also they can talk to Docker Host. 
* **Creating a Docker Network**: Use `docker network create <network_name>` to create a user-defined bridge network.
* **Connecting a Container to Network**: Use `docker run -d --name <name_of_host> --network <network_name> -p <port_to_be>:<port_exposec> <image_name>`.
* **Types of Volumes**:
  - Bridge Network: Default network type, allows containers to communicate with eachother on same host but isolates them from external networks
  - Host Network: Uses the host computer network stack uses IP Address
  - Overlay Network: Conatiners on different computers can talk to each other as if they are on same network; use when you're running a system across multiple machine like distributed systems or docker swarm clusters
  - Macvlan Network: Assigns a unique MAC address to container, making them appear as physical devices; use when integrating conatiner into a network requiring direct access
  - None Network: The containers has no network connection at all; use when your container doesn't need to communicate at all

## Build Docker Network
* In this project, we are trying to create bridge networks
  - ![](https://github.com/srikanth-josyula/springboot-docker-projects/blob/docker-networking/docs/Images/create-network-default-driver.png)

## Build Docker Image
* Navigate to the directory containing your Dockerfile and run docker build command `docker build -t <your-image-name>:<tag> .` for 2 images
  - `-t your-image-name:tag`: Tags the image with a name and a version tag (e.g., myapp:latest). You can replace your-image-name with a name that makes sense for your project.
  - `.`: The dot signifies the build context, which is the current directory.
  - `docker build -t docker-volume-management:v1 .`
  - ![](]https://github.com/srikanth-josyula/springboot-docker-projects/blob/docker-networking/docs/Images/build-images.png)

## Run the Docker Container
* To run your application in a container, use the docker run command: 
  - ![](https://github.com/srikanth-josyula/springboot-docker-projects/blob/docker-networking/docs/Images/docker-runapp1.png)
  - ![](https://github.com/srikanth-josyula/springboot-docker-projects/blob/docker-networking/docs/Images/dockerrun-appp2.png)

* Open your web browser access application running in the Docker container.
  - ![](https://github.com/srikanth-josyula/springboot-docker-projects/blob/docker-networking/docs/Images/outputapp1.png)
  - ![](https://github.com/srikanth-josyula/springboot-docker-projects/blob/docker-networking/docs/Images/outputapp2.png)
    
## Stop Docker Container
* We can list down all docker containers by command `docker ps` in the terminal and we can use command `docker stop container-name`
