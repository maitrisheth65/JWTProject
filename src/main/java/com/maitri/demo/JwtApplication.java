package com.maitri.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
/*
 * Spring boot main class.
 */
@EntityScan("com.maitri.model")
@ComponentScan("com.maitri")
@EnableJpaRepositories("com.maitri.dao")
@SpringBootApplication
public class JwtApplication  {
	public static void main(String[] args) {
		SpringApplication.run(JwtApplication.class, args);
	}

}
