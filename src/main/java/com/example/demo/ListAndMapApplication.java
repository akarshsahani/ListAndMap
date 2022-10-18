package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableAutoConfiguration
//@ComponentScan(basePackages="com.nice.application")
//@EnableJpaRepositories("com.spring.app.repository")
public class ListAndMapApplication {

	public static void main(String[] args) {
		SpringApplication.run(ListAndMapApplication.class, args);
	}

}
