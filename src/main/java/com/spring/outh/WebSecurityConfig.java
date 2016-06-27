/**
 * 
 */
package com.spring.outh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

/**
 * @author sanket
 *
 */
@EnableWebSecurity
@EnableOAuth2Client
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	OAuth2ClientContextFilter oAuthcontextFilter;
	
	@Autowired
	AuthFilter auth;
	
	@Autowired
	Resource resource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.antMatcher("/**")
		.authorizeRequests()
		.antMatchers("/", "/login**", "/webjars/**")
		.permitAll()
		.anyRequest()
		.authenticated()
		.and().logout().logoutSuccessUrl("/").permitAll()
		.and().csrf().csrfTokenRepository(resource.csrfTokenRepository())
		.and()
		.addFilterBefore(auth.ssoFilter(), BasicAuthenticationFilter.class)
		.addFilterAfter(oAuthcontextFilter, SecurityContextPersistenceFilter.class);
	}
	
}
