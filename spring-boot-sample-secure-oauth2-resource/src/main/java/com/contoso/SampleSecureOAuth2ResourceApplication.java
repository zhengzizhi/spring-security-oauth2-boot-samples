package com.contoso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@SpringBootApplication
@EnableResourceServer
public class SampleSecureOAuth2ResourceApplication
		extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.antMatcher("/flights/**")
			.authorizeRequests()
				.anyRequest().authenticated();
	}

	public static void main(String[] args) {
		SpringApplication.run(SampleSecureOAuth2ResourceApplication.class, args);
	}

}
