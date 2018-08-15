package com.contoso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableOAuth2Sso
public class SampleGithubSecureApplication implements WebMvcConfigurer {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SampleGithubSecureApplication.class, args);
	}

}
