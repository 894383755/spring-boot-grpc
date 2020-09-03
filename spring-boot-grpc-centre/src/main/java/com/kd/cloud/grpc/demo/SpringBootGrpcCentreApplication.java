package com.kd.cloud.grpc.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringBootGrpcCentreApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootGrpcCentreApplication.class, args);
	}

}
