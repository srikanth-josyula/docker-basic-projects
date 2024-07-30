# Sample SpringBoot Docker Example

## Docket Setup Guide
* Docker packages applications with their runtime environments, ensuring consistent deployment across systems without runtime conflicts. Unlike virtual machines, Docker containers run directly on the host OS without an additional OS layer.
* Use Docker Desktop for modern systems with advanced features, Docker Toolbox for older systems, and Docker Engine for Linux, avoiding installation of both Docker Toolbox and Docker Desktop on the same system to prevent conflicts.
* Follow the guide for installing Docker Desktop: [Docker Installation Guide.pdf](https://github.com/srikanth-josyula/springboot-docker-kubernetes/blob/docker-basic-setup/docs/Docker%20Installation%20Guide.pdf)
* Ensure Docker is installed on your system. You can check if Docker is installed by running: `docker --version`
  
### Docker Terms
* **Docker Engine**: The core component that creates and runs Docker containers. It includes the Docker daemon, REST API, and CLI.
* **Docker Images**: Immutable templates for containers, created using a Dockerfile, which define the environment and application to be run.
* **Docker Containers**: Executable packages that run applications and their dependencies isolated from the host system.
* **Dockerfile**: A script containing instructions to assemble a Docker image. It specifies the base image, software installations, and configurations.

## Build Docker Image
* Create a file named Dockerfile in your project directory, Add the required instructions in it
* Navigate to the directory containing your Dockerfile and run the docker build command `docker build -t <your-image-name>:<tag> .`
  - `-t your-image-name:tag`: Tags the image with a name and a version tag (e.g., myapp:latest). You can replace your-image-name with a name that makes sense for your project.
  - `.`: The dot signifies the build context, which is the current directory.
  - ![alt text](https://github.com/srikanth-josyula/springboot-docker-demo/blob/master/docs/images/running-servers.PNG?raw=true)


### Stop Docker Container
* We can list down all docker containers by command docker ps in the terminal and we can use command docker stop <name>

![alt text](https://github.com/srikanth-josyula/springboot-docker-demo/blob/master/docs/images/running-servers.PNG?raw=true)
