package com.grupo4.apigateway;

import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.mockito.InjectMocks;
import org.mockito.Mock;


@SpringBootApplication
public class ApiGatewayApplication {

	
	
	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
	
	@Test
	public void probar() {
		System.out.println("Cargando Api Gateway...");
		System.out.println("spring.cloud.gateway.routes[0].predicates[0]=Path=/api/current/**;");
		return;
	}

}
