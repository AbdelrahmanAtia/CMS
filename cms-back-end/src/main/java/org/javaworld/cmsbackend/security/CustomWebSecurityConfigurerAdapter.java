package org.javaworld.cmsbackend.security;

import org.javaworld.cmsbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;
		
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {	
		
		httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();

		httpSecurity.authorizeRequests()
							.antMatchers(HttpMethod.OPTIONS).permitAll()  // no need for authorization token for options request
							.antMatchers("/api/authentication/login").permitAll()
							.antMatchers("/api/products/products_images/*").permitAll()
							.antMatchers("/api/categories/categories_images/*").permitAll()
							.antMatchers("/api/configs/logs/*").permitAll()
			                .antMatchers("/h2-console/**").permitAll()
			                .antMatchers("/actuator/**").permitAll()
							.antMatchers("/api/users/**").hasAuthority("Administrator")
					.anyRequest().authenticated()
					.and().httpBasic(); // basic authentication
		
		 httpSecurity.exceptionHandling().authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint());
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Set your configuration on the auth object
		
		/*
		auth.inMemoryAuthentication().withUser("blah")
							         .password("blah")
							         .roles("USER")
							         .and()
							         .withUser("foo")
							         .password("foo")
							         .roles("ADMIN");
		 */
		
		auth.userDetailsService(userService);
	}
	
	
	@Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
    
}
