package com.contoso.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
public class AuthenticationConfiguration {

	@Bean
	UserDetailsService userDetailsService() {
		UserDetails jack = User.withDefaultPasswordEncoder()
				.username("jack")
				.password("abc123")
				.roles("read","write")
				.build();
		return new InMemoryUserDetailsManager(jack);
	}
}