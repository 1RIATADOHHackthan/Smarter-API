package com.hackathon.smarter;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class SmarterApplication {

	public static void main(String[] args) {
		//SpringApplication.run(SmarterApplication.class, args);
		SpringApplication app = new SpringApplication(SmarterApplication.class);
        app.setDefaultProperties(Collections
          .singletonMap("server.port", "8083"));
        app.run(args);
	}

}
