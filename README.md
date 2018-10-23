# Spring-Microservices-Architecture-Demo
I developed this small project in order to learn the basics of a microservice architecture using Spring boot.

## :one: About microservice architecture
### :star: Definition
- In computing, microservices are **small**, **independent processes** that communicate with each other to form complex applications which utilize language-agnostic APIs.
- These services are **small building blocks**, highly **decoupled** and focused on doing a **small task**, facilitating a **modular approach to system-building**.

### :+1: Characteristics of a microservice
Each microservice is **relatively small** (easier for a developer to understand, the application starts faster, which makes developers more productive, and speeds up deployments).
Each service **can be deployed independently of other services** - easier to deploy new versions of services frequently.
Each service can be managed by a **small team from development to maintenance** (**DevOps approach**).
Improved **fault isolation**. If a service fails, the other services will continue to handle requests.
Each service can be developed and deployed independently.
Each service can use his own technology stack.

### :-1: Drawbacks

**Developers must deal with the additional complexity of creating a distributed system :**
- Testing is more difficult
- Developers must implement the inter-service communication mechanism.
- Implementing use cases that span multiple services without using distributed transactions is difficult
- Implementing use cases that span multiple services requires careful coordination between the teams

**The deployment complexity** : In production, there is also the operational complexity of deploying and managing a system comprised of many different service types.

**Increased memory consumption :** The microservice architecture replaces N monolithic application instances with NxM services instances. If each service runs in its own JVM (or equivalent), which is usually necessary to isolate the instances, then there is the overhead of M times as many JVM runtimes. Moreover, if each service runs on its own VM (e.g. EC2 instance), as is the case at Netflix, the overhead is even higher.

## :two: Registration Service
How do the clients of a service know which instances are available for that service? The ports of these instances ? The addresses of these instances ?
In many cases, the number of instances and their location can change dynamically. Sometimes virtual machines and containers are assigned dynamic IP addresses. An EC2 Autoscalling Group, for example, adjusts the number of instances according to the load.

### What is a registration service?
A registration service is a database of services, their instances and locations. Service instances are registered via the service registry at startup and are finally removed from this registry when they stop.

#### How it works ?
The client of a service makes a request to the service registry to find the available instances of a service. A service registry must invoke a health check of the API of the service instance to verify that the service is able to process requests. This implies that a service has an endpoint that returns its state of health. The API endpoint performs several checks such as:
- The status of the inter-connection between this service and the services it contains (e.g. connection to the database, connection status to the smtp server, etc.).
- The status of the host (e.g. disk space, memory usage, etc.). 

As a client of the health endpoint, the service registry periodically invokes the endpoint to verify the health status of the instance.

## :three: Spring Cloud
Spring Cloud Netflix provides Netflix OSS integrations for Spring Boot applications by autoconfiguration.

With a few simple annotations, we can activate and configure common patterns within our application and build large distributed systems with tested and proven Netflix components.

The models provided include service discovery (Eureka), circuit breaker (Hystrix), intelligent routing (Zuul) and client-side load balancing (Ribbon).

## :four: About this project
For this project I made 3 different services as well as another service that does not register with the service registry that is dedicated to monitoring.

The first (eureka-service-registry), as its name suggests, sets up the registry service.

The second (film-service), is a simple API with its database that allows you to search for films.

The third (service-aggregator), is simply the service that customers will call so that the latter will get the information via the right service (if we add a service, for example series-service, we will go through it again to access series-service).

And finally, the last one (monitoring service), simply sets up the spring-boot-admin-starter-server library. All other services will register there so that monitoring-service knows them and can retrieve their information thanks to the spring-boot-starter-actuator library. We thus obtain a web interface that allows us to monitor the status of our services.

## Web interfaces
Each service offers a Swagger interface (except monitoring service):

 - eureka-service-registry
	 - Eureka : http://127.0.0.1:8761/
	 - Swagger UI : http://127.0.0.1:8761/swagger-ui.html
- service-aggregator
	- Swagger UI : http://127.0.0.1:8080/swagger-ui.html
- film-service:
	- Swagger UI : http://127.0.0.1:8081/swagger-ui.html
- monitoring-service
	- Monitoring interface : http://127.0.0.1:8079/admin
