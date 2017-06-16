package com.example.kubedemo;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Configuration
public class KubeDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(KubeDemoApplication.class, args);
	}


	@Bean
	RestTemplate restTemplate(){
		return new RestTemplate();
	}
}
