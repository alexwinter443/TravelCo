package com.gcu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.gcu.business.UsersBusinessService;

/*
 * Kacey Morris and Alex Vergara
 * Milestone
 * 11/29/2021
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	// to hash passwords
	@Autowired
	PasswordEncoder passwordEncoder;
	
	// to manipulate the users
	@Autowired
	UsersBusinessService service;
	
	// to hash passwords
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// security configurations
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.authorizeRequests()
			// put most restricted pages first. Only ADMIN can add, delete, or edit products
			// THIS DOES NOT WORK
//			.antMatchers("/products/admin").hasAnyRole("ADMIN")
//			.antMatchers("/products/createProduct").hasAnyRole("ADMIN")
			
			// WORKING ALTERNATIVE
			// https://stackoverflow.com/questions/30788105/spring-security-hasrole-not-working
			.antMatchers("/products/admin").hasAuthority("ADMIN")
			.antMatchers("/main/admin").hasAuthority("ADMIN")
			.antMatchers("/products/createProduct").hasAuthority("ADMIN")
			
			
			// settings for the API, only admin can add a new role, update, and delete
			.antMatchers(HttpMethod.POST, "/api/**").hasAuthority("ADMIN")
			.antMatchers(HttpMethod.PUT, "/api/**").hasAuthority("ADMIN")
			.antMatchers(HttpMethod.DELETE, "/api/**").hasAuthority("ADMIN")
			
			// only authenticated users can access the api
			.antMatchers("/api/**").authenticated()
			// only authenticated users can access the products
			.antMatchers("/products/**").authenticated()
			
			// allow non logged in users to see the login page, register page, and image folders
			.antMatchers("/main/login", "/main/register", "/img/**").permitAll()
			.and()
			.httpBasic()
			.and()
			.formLogin()
			// added to specify login form
			// used the URL that is served by the login controller
			.loginPage("/main/login")
				
			// match the text input fields on the login form
			.usernameParameter("username")
			.passwordParameter("password")
			.permitAll()
			
			// display all orders after login
			.defaultSuccessUrl("/products/all")
			
			// dont use csrf in an API since csrf is used to secure <form> items not JSON data
			.and()
			.csrf().ignoringAntMatchers("/api/**");
	}
	
	// @SuppressWarnings("deprecation")
	// encrypt the passwords
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		String password = new BCryptPasswordEncoder().encode("123");
		// for entering the encrypted value into the database
		// extend to registration?
		System.out.println("== Encrypted value of 123 ===== " + password + " ==");
		auth
		.userDetailsService(service)
		.passwordEncoder(passwordEncoder);
		// this works V
//		auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
//			.withUser("thisisatest").password("123").roles("ADMIN").and()
//			.withUser("anothertest").password("123").roles("USER");
	}
}
