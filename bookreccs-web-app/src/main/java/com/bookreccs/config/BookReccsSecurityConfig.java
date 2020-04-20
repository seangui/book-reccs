package com.bookreccs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bookreccs.service.UserService;


@Configuration
@EnableWebSecurity
public class BookReccsSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler; 
	
	// configures users in memory database etc. 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	
	// configures security of web paths in application, login, lougout etc.
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// this allows our /create-acc to be accessed without being authenticated 
		// and for any of our resources like css to pass through spring security filters
		http
			.authorizeRequests()
			.antMatchers("/resources/**", "/register/**", "/webjars/**")
			.permitAll(); 
		
		http
			.authorizeRequests()
			.anyRequest().authenticated() // this part means any request coming into the app the user must be authenticated aka logged in
			
			.and() 
			
			.formLogin() // this indicates we're customizing our login form and using our own and not the provided one
			.loginPage("/login") // show our custom form at the request mapping, spring looks into our controller for this
			.loginProcessingUrl("/authenticate-login") // checks user id and password - does not need to be made in controller, its provided by spring
			.successHandler(customAuthenticationSuccessHandler)
			.permitAll() // all users with all authorizations are allowed to see this form - no need to be logged in 
			
			.and()
			
			.logout()
			.permitAll()
		
			.and()
			.exceptionHandling().accessDeniedPage("/access-denied");
	}
	
	//beans
	//bcrypt bean definition
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	//authenticationProvider bean definition
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService); //set the custom user details service
		auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
		return auth;
	}
	  
}