package fr.alexisvachard.microservices.eurekaserviceregistry;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
@EnableDiscoveryClient
@EnableAdminServer
public class EurekaServiceRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServiceRegistryApplication.class, args);
    }
}
