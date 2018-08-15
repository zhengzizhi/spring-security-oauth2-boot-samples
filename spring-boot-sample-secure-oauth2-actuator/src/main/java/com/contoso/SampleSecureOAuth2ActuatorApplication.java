package com.contoso;

import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableResourceServer
@RestController
public class SampleSecureOAuth2ActuatorApplication {

	@GetMapping("/")
	public Message home() {
		return new Message("Hello World");
	}

	@Bean
	UserDetailsService userDetailsService() throws Exception {
		UserDetails user = User.withDefaultPasswordEncoder()
			.username("user")
			.password("password")
			.roles("USER")
			.build();
		return new InMemoryUserDetailsManager(user);
	}

	public static void main(String[] args) {
		SpringApplication.run(SampleSecureOAuth2ActuatorApplication.class, args);
	}

	class Message {

		private String id = UUID.randomUUID().toString();

		private String value;

		public Message(String value) {
			this.value = value;
		}

		public String getId() {
			return this.id;
		}

		public String getValue() {
			return this.value;
		}

	}

}
