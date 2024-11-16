package com.example.productsales.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
@EnableWebSecurity
public class SecurityConfig4 {
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable() 
            .authorizeRequests()
                .requestMatchers("/api/products/**", "/api/sales/**").hasRole("ADMIN")  
                .requestMatchers("/api/products/**", "/api/sales/**").permitAll() 
                .anyRequest().authenticated() 
            .and()
            .httpBasic();  

        return http.build();
    }
	
	 @Bean
	    public UserDetailsService userDetailsService() {
	        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

	       
	        UserDetails admin = User.withUsername("admin")
	                .password("{noop}password")  
	                .roles("ADMIN")
	                .build();
	        
	        UserDetails user = User.withUsername("user")
	                .password("{noop}password") 
	                .roles("USER")
	                .build();

	        manager.createUser(admin);
	        manager.createUser(user);
	        
	        return manager;
	    }
}
