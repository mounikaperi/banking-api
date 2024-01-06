package com.springbanking.easymoney;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
@EnableJpaRepositories("com.springbanking.easymoney.repository")
@EntityScan("com.springbanking.easymoney.model")
public class EasyMoneyApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyMoneyApplication.class, args);
	}

}
