package com.seb.blog;

import com.seb.blog.github.GitProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.dialect.springdata.SpringDataDialect;

@EnableConfigurationProperties(GitProperties.class)
@SpringBootApplication
public class SebBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(SebBlogApplication.class, args);
	}

	@Bean
	public SpringDataDialect springDataDialect() {
		return new SpringDataDialect();
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
