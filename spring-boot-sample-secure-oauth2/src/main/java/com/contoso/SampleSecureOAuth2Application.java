package com.contoso;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * After you launch the app, you can seek a bearer token like this:
 *
 * <pre>
 * curl -i localhost:8080/oauth/token -d "grant_type=password&scope=read&username=jack&password=abc123" -u foo:bar
 * </pre>
 *
 * <ul>
 * <li>grant_type=password (user credentials will be supplied)</li>
 * <li>scope=read (read only scope)</li>
 * <li>username=greg (username checked against user details service)</li>
 * <li>password=turnquist (password checked against user details service)</li>
 * <li>-u foo:bar (clientid:secret)</li>
 * </ul>
 *
 * Response should be similar to this:
 * <code>{"access_token":"533de99b-5a0f-4175-8afd-1a64feb952d5","token_type":"bearer","expires_in":43199,"scope":"read"}</code>
 *
 * With the token value, you can now interrogate the RESTful interface like
 * this:
 *
 * <pre>
 * curl -H "Authorization: bearer [access_token]" localhost:8080/flights/1
 * </pre>
 *
 * You should then see the pre-loaded data like this:
 *
 * <pre>
 * {
 *      "origin" : "Nashville",
 *      "destination" : "Dallas",
 *      "airline" : "Spring Ways",
 *      "flightNumber" : "OAUTH2",
 *      "date" : null,
 *      "traveler" : "Greg Turnquist",
 *      "_links" : {
 *          "self" : {
 *              "href" : "http://localhost:8080/flights/1"
 *          }
 *      }
 * }
 * </pre>
 *
 * Test creating a new entry:
 *
 * <pre>
 * curl -i -H "Authorization: bearer [access token]" -H "Content-Type:application/json" localhost:8080/flights -X POST -d @flight.json
 * </pre>
 *
 * Insufficient scope? (read not write) Ask for a new token!
 *
 * <pre>
 * curl localhost:8080/oauth/token -d "grant_type=password&scope=write&username=greg&password=turnquist" -u foo:bar
 *
 * {"access_token":"cfa69736-e2aa-4ae7-abbb-3085acda560e","token_type":"bearer","expires_in":43200,"scope":"write"}
 * </pre>
 *
 * Retry with the new token. There should be a Location header.
 *
 * <pre>
 * Location: http://localhost:8080/flights/2
 *
 * curl -H "Authorization: bearer [access token]" localhost:8080/flights/2
 * </pre>
 *
 * @author Craig Walls
 * @author Greg Turnquist
 */
@SpringBootApplication
@EnableAuthorizationServer
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RestController
public class SampleSecureOAuth2Application 	extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.antMatcher("/flights/**")
			.authorizeRequests()
				.anyRequest().authenticated();
	}
	@GetMapping("/user")
	public Principal user(Principal user) {
		return user;
	}

	public static void main(String[] args) {
		SpringApplication.run(SampleSecureOAuth2Application.class, args);
	}

}
