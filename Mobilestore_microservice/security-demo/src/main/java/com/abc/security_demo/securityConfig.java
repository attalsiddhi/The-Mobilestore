package com.abc.security_demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class securityConfig {

	
	@Bean
	UserDetailsService appUserDetails() {
		
		InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
		
		userDetailsManager.createUser(User.withUsername("siddhi").password("{noop}siddhi123").roles("USER").build());
		userDetailsManager.createUser(User.withUsername("sam").password("{noop}sam123").roles("USER").build());
		userDetailsManager.createUser(User.withUsername("rohit").password("{noop}rohit123").roles("USER","ADMIN").build());
		
		return userDetailsManager;
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((authorize)->
		authorize.requestMatchers("/api/user/**").hasAnyRole("USER")
		.requestMatchers("/api/admin/**").hasAnyRole("ADMIN")
		.requestMatchers("/api/home").permitAll()
		.anyRequest().authenticated()).httpBasic(Customizer.withDefaults()).formLogin(Customizer.withDefaults());
		
		return http.build();
	}
}
