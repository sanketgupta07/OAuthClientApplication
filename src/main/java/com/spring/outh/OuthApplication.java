package com.spring.outh;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication

//@EnableOAuth2Sso
@RestController
public class OuthApplication extends SpringBootServletInitializer {

	@RequestMapping("/user")
	public Principal user(Principal principal) {
		System.out.println("Principal:"+principal.getName());

		return principal;
	}

	public static void main(String[] args) {
		SpringApplication.run(OuthApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(OuthApplication.class);
	}
}
