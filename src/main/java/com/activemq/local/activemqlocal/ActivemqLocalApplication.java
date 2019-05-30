package com.activemq.local.activemqlocal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
@EnableAutoConfiguration
public class ActivemqLocalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivemqLocalApplication.class, args);
	}

}
