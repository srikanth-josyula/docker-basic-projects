# SpringBoot REST Log4J Docker with Volumes Example

## Project Overview
* This project demos about the usage of Volumes with Springboot Log4j Applicaiton, This Project helps in TIFF to PDF conversions, utilizing the concept of docker volumes to store the temp file for the conversion, and returns downloadable PDF on REST call
* Also This project demos the usage of creation and mounting the volumes for the purpose of storing the logs
  
### Docker Volume Terms
* **Docker Volume**: Docker volumes persist data beyond container lifecycles, allowing data to be retained and shared between containers and the host machine, with advantages such as easy backup, recovery, and consistent storage management. Docker volumes are managed by the Docker daemon and can be created, inspected, and manipulated using Docker CLI commands or Docker Compose configurations.
* **Creating a Docker Volume**: Use `docker volume create <volume_name>` to create a named volume, stored in /var/lib/docker/volumes.
* **Attaching a Volume**: Use `docker run -v <volume_name>:<container_folder> <image_name>` for named volumes or `docker run --mount type=bind,source=<volume_name_or_path>,target=<container_folder> <image_name> for bind mounts`.
* **Types of Volumes**:
  - Named volumes (docker volume create my_volume)
  - bind mounts (docker run --mount type=bind,source=/path/on/host,target=/app/data my_image), and
  - anonymous volumes (docker run -v /app/data my_image).

## Build Docker Voumes
* In this project, we are trying to create volumes using Dockerfiles VOLUME command, to store convertion temp files and other is Named Volumes to store logs
* **Volume for Temporary Files (/temp)**: Defined in the Dockerfile with VOLUME /temp. This volume is created automatically by Docker when you run the container, but it’s local to the container unless explicitly mounted to a host directory.
  - We define `VOLUME /temp` in our Dockerfile
  - The VOLUME instruction in a Dockerfile declares a directory as a mount point and creates a volume for it when the container is run. This volume will be created inside the container’s storage and doesn’t persist outside unless explicitly mounted.
  - ![](https://github.com/srikanth-josyula/springboot-docker-projects/blob/docker-volume-management/docs/Images/volumes-inconatiner.png?raw=true)
* **Docker Volume for Logs (/logs)**: Created using the docker volume create command and mounted when running the container. This volume persists data outside of the container’s lifecycle.
  - The volume created with docker volume create and mounted at /logs will persist data independently of the container's lifecycle. This is useful for data that needs to be retained across container restarts or removals. 
  - Run the below command to creat a logs_volume `docker volume create logs_volume`
  - ![](https://github.com/srikanth-josyula/springboot-docker-projects/blob/docker-volume-management/docs/Images/create-volume.png?raw=true)

## Build Docker Image
* Create a file named [Dockerfile](https://github.com/srikanth-josyula/springboot-docker-projects/blob/docker-volume-management/Dockerfile)
* We are trying to copy our application.properties from local to container
  - ![](https://github.com/srikanth-josyula/springboot-docker-projects/blob/docker-volume-management/docs/Images/app_properties.png?raw=true)
* Navigate to the directory containing your Dockerfile and run docker build command `docker build -t <your-image-name>:<tag> .`
  - `-t your-image-name:tag`: Tags the image with a name and a version tag (e.g., myapp:latest). You can replace your-image-name with a name that makes sense for your project.
  - `.`: The dot signifies the build context, which is the current directory.
  - `docker build -t docker-volume-management:v1 .`
  - ![](https://github.com/srikanth-josyula/springboot-docker-projects/blob/docker-volume-management/docs/Images/image-creation.png?raw=true)

## Run the Docker Container
* To run your application in a container, use the docker run command: `docker run`
* `docker run -d -v logs_volume:/logs -p 6090:6090 -t docker-volume-management:v1`
 - `-v logs_volume:/logs` mounts the logs_volume Docker volume to /logs inside the container.
 - `-p 6090:6090` maps the container's port to the host.
  - ![](https://github.com/srikanth-josyula/springboot-docker-projects/blob/docker-volume-management/docs/Images/run.png?raw=true)
* Volumes are created as below under volumes 
  - ![](https://github.com/srikanth-josyula/springboot-docker-projects/blob/docker-volume-management/docs/Images/volumes.png?raw=true)
  - ![](https://github.com/srikanth-josyula/springboot-docker-projects/blob/docker-volume-management/docs/Images/log-volume.png?raw=true)

* Open your web browser access application running in the Docker container.
  - ![](https://github.com/srikanth-josyula/springboot-docker-projects/blob/docker-volume-management/docs/Images/output.png?raw=true)
* We can see the logs, where the volume is accessed
  - ![](https://github.com/srikanth-josyula/springboot-docker-projects/blob/docker-volume-management/docs/Images/response-logs.png)

## Stop Docker Container
* We can list down all docker containers by command `docker ps` in the terminal and we can use command `docker stop container-name`
