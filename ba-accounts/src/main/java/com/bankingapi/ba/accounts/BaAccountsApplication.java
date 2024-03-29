package com.bankingapi.ba.accounts;

import com.bankingapi.ba.accounts.dto.AccountsContactInfoDTO;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableFeignClients
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {AccountsContactInfoDTO.class})
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts microservice REST API documentation",
				description = "EazyBank Accounts microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Sai Mounika Peri",
						email = "xyz@gmail.com",
						url = "https://www/xyz.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.xyz.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "EazyBank Accounts Microservice REST API Documentation",
				url = "https://www.xyz.com/swagger-ui.html"
		)
)
public class BaAccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaAccountsApplication.class, args);
	}
}
