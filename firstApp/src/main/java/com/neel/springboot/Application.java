package com.neel.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.neel")
public class Application {

	public static void main(String[] args) {
		System.setProperty("server.port", "8081");
		ApplicationContext ctx = SpringApplication.run(Application.class, args);

	}

}