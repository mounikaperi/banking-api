package com.bankingapi.baeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class BaEurekaserverApplication {
	public static void main(String[] args) {
		SpringApplication.run(BaEurekaserverApplication.class, args);
	}

}
