# Sample SpringBoot Docker Example

## Introduction
* Docker is a developer tool to package applications along with their runtime environment, so anybody can deploy and run them in any other machine without facing runtime environment conflicts. It is very similar to virtual machine concept 'virtualization', where you can get a VM image and run it on any supporting hardware. All internal programs in VM will function as they were packaged originally.
* Docker Containers are developed by the developers, These containers can be used throughout the SDLC (Dev Env, QA Env, Prod Env) lifecycle providing a consistency. There won't be any difference in computing environment 
* Docker containers are similar in nature to virtual machines. However, a virtual machine has an extra OS in the total stack. A virtual machine has a VM OS, and then the VM is running on some computer which also has its own OS.
* A Docker container, on the other hand, does not have its own internal OS. The container runs directly inside the host Linux OS. Thus, a Docker container is smaller in size, since it does not contain the whole VM OS. Docker containers can also perform better, as there is no virtualization of the VM necessary.

### Docker Terms
* Docker allows users to publish docker images and consume those published by others in repositories like Docker Hub.
* Developer writes a code and defines the application requirments or dependencies in an easy to write **Dockerfile**. This Dockerfile produces docker images and docker containers
* A **Docker Image** is a portable, executable recipe for a Docker container (that contains all the dependencies of the application). The Docker image contains all needed files and instructions to run the corresponding Docker container.
* A **Docker Container** are the runtime instance of the Docker Images.
* Docker Images are uploaded to the **Docker Hub (Docker Registry)**, Location for [DockerHub](https://hub.docker.com/). A Docker Registry is a Docker image repository where Docker images can be uploaded to, and also downloaded from. A Docker Registry can be either private or public. From the Docker Hub different teams can pull their images and deploy in containers 

![alt text](https://github.com/srikanth-josyula/springboot-docker-demo/blob/master/docs/images/docker-architecture.PNG?raw=true)

## Docker Installation

### Download Docker installer
* For windows 10 we need to follow this [link](https://docs.docker.com/docker-for-windows/)
* For Windows 7, 8 and older versions we need to use Docker Toolbox and here is the official [link](https://docs.docker.com/toolbox/overview/)

### Enable Hardware Virtualization Technology
* In order to Docker toolbox works properly we need to make sure your Windows system supports Hardware Virtualization Technology and that virtualization is enabled. 
* If we don’t have this enabled then we need to go to BIOS option and enable Hardware Virtualization. The BIOS is bit different for different models of Computer, so please follow official guideline for enabling that.

### Verify your installation
* To verify docker installation, open Docker Quickstart Terminal shortcut. Verify that Docker prompt is coming and then need to test few basic commands. Docker prompt and sample docker command will look like below.

![alt text](https://github.com/srikanth-josyula/springboot-docker-demo/blob/master/docs/images/docker-installation-verification.PNG?raw=true)

* We need to now note down the Docker IP assigned to this Container. We will access this IP to access the Applications installed inside Docker. To know the IP from the command prompt use command docker-machine ip. Here is the sample output of the command. Please note that this IP will be different for different M/Cs. As below

![alt text](https://github.com/srikanth-josyula/springboot-docker-demo/blob/master/docs/images/docker-machine-ip-output.PNG?raw=true)

## Create docker Image

### Create Spring REST Project
* Create Spring REST Project, with sample requestMapping
* Now test this microservice by running the project as spring boot application.

### Add Docker Configurations
* Now create a file named Dockerfile in the **Root Directory**, A Dockerfile is a text document that contains all the commands a user could call on the command line to assemble an image. Using docker build users can create an automated build that executes several command-line instructions in succession.
* The docker build command builds an image from a Dockerfile and a context.

### Dockerfile Configurations
* A Dockerfile captures the recipe for building a Docker image in writing. A Docker image typically consists of:
* A base Docker image on top of which to build your own Docker image. 
* A base Docker image on top of which to build your own Docker image.
* A set of tools and applications to be installed in the Docker image.
* A set of files to be copied into the Docker image (e.g configuration files).

### Dockerfile Structure
* A Dockerfile consists of a set of instructions. Each instruction consists of a command followed by arguments to that command, similar to command line executables.


| Command    | Explanation                                                           						  	   | Example                                     							   			    			    		   |
|------------|---------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------|
| MAINTAINER | Used to tell who is maintaining this file     													   | MAINTAINER   Joe Blocks <joe@blocks.com>   			    			    			    			    	   |
| FROM       | Specifies the base image of your Docker images 				        						 	   | FROM ubuntu:latest         				   			    			    			    		    	       |
| CMD        | Command line command to execute when a Docker container is started up							   | CMD echo Docker container started. 			         			       			       			       		   |	
| COPY       | Copies one or more files from the Docker host						 			               	   | COPY    /myapp/target/myapp.jar    /myapp/myapp.jar  			       			       			       			   |	
| ADD        | Copy and extract TAR files from the Docker host to the Docker image or and we can download from HTTP| ADD    myapp.tar    /myapp/ 			       			       			       			       			       	   |	
| ENV        | ENV command can set an environment variable inside the Docker image.								   | ENV    MY_VAR   123 			       			       			       			       			       			   |	
| RUN        | Execute command line executables within the Docker image											   | RUN apt-get install some-needed-app			       			       			       			       			   |
| WORKDIR    | Specifies what the working directory should be inside the Docker image.							   | WORKDIR    /java/jdk/bin			       			       			       			       			       		   |
| EXPOSE     | Opens up network ports in the Docker container to the outside world								   | EXPOSE   8080 			       			       			       			       			       			           |
| VOLUME     | Creates a directory inside the Docker image, later mount a volume  to from the Docker host		   | VOLUME   /data 			       			       			       			       			       			       |
| ENTRYPOINT | Command that is executed when the Docker container is started up									   | ENTRYPOINT [ "sh", "-c", "java -jar /springboot-docker-demo.jar" ] 			       			       			   |
| HEALTHCHECK| Command at regular intervals, to monitor the health of the app inside the Docker container		   | HEALTHCHECK java -cp /apps/myapp/springboot-docker-demo.jar com.sample.HealthCheck https://localhost/healthcheck  |


### Add Maven Docker Plugins
* Add two maven plugins in the pom.xml file so that we can use the Docker related maven commands while creating the instance. Those plugins are dockerfile-maven-plugin and maven-dependency-plugin.

![alt text](https://github.com/srikanth-josyula/springboot-docker-demo/blob/master/docs/images/plugins.PNG?raw=true)

### Create Docker Image
* Now use maven command **mvn clean install dockerfile:build** to create docker image.

![alt text](https://github.com/srikanth-josyula/springboot-docker-demo/blob/master/docs/images/docker-image-from-terminal.PNG?raw=true)
![alt text](https://github.com/srikanth-josyula/springboot-docker-demo/blob/master/docs/images/docker-image-build-logs.PNG?raw=true)

## Deploy and Run Docker Image
* So we have created the Docker Image (i.e. springboot-docker-demo-0.0.1-SNAPSHOT-docker-info.jar). We also have a installed docker container running in our local machine.
* Now, to run the docker image inside installed docker container, we will use below command.

**docker run -p 8080:8181 -t springboot-docker-demo/springboot-docker-demo  --name springboot-docker-demo-image**

**docker run -p 8080:<serverPort> -t <docker.image.prefix>/<project-artifactId>  --name project-artifactId-image**

* Here the option -p 8080:8181 is important. It says that expose port 8080 for internal port 8181. Remember our application is running in port 8181 inside docker image and we will access that in port 8080 from outside Docker container.

![alt text](https://github.com/srikanth-josyula/springboot-docker-demo/blob/master/docs/images/starting-docker-image.PNG?raw=true)

* Now access the application with URL http://<ipAddress>:8080/hello/srikanth. Notice that the browser output is same as output of standalone REST API on localhost. (192.168.99.100)

![alt text](https://github.com/srikanth-josyula/springboot-docker-demo/blob/master/docs/images/output.PNG?raw=true)

### Stop Docker Container
* We can list down all docker containers by command docker ps in the terminal and we can use command docker stop <name>

![alt text](https://github.com/srikanth-josyula/springboot-docker-demo/blob/master/docs/images/running-servers.PNG?raw=true)
