package com.michelin.mic.projeto.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

		httpSecurity
	        .authorizeRequests().antMatchers("/form/**").hasRole("ADMIN")
	        .and()
	        .authorizeRequests().antMatchers("/form-add/**").hasRole("ADMIN")
	        .and()
	        .authorizeRequests().antMatchers("/edit/**").hasRole("ADMIN")
	        .and()
	        .formLogin().loginPage("/login").permitAll().successForwardUrl("/form");
		
		httpSecurity.csrf().disable();
		httpSecurity.headers().frameOptions().disable();
    }

	@Bean
    @Override
    public UserDetailsService userDetailsService() {
        @SuppressWarnings("deprecation")
		UserDetails user =
             User.withDefaultPasswordEncoder()
                .username("admin")
                .password("P@ssw0rd")
                .roles("ADMIN")
                .build();
        
        return new InMemoryUserDetailsManager(user);
    }
	
}
