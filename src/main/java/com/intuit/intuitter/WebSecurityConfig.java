package com.intuit.intuitter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.userdetails.InetOrgPersonContextMapper;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

@Configuration
@EnableGlobalAuthentication
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	/* we can skip authorization check if the LDAP setup isnt working.*/
	@Value("#{'${skip.authorization.check}'}")
	boolean skipAuthorizationCheck = false;		

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		if (skipAuthorizationCheck) {
			return;
		}
		
		http
			.authorizeRequests()
			.anyRequest()
			.fullyAuthenticated()
			.and()
			.formLogin()
			.successHandler(new SavedRequestAwareAuthenticationSuccessHandler())
			
			//following will make subsequent requests, after login, redirec to login if request is not authorized.
//			.and()
//			.exceptionHandling()
//            .accessDeniedPage("/login")
            ;
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		 auth
		 	.ldapAuthentication()
			.userDetailsContextMapper(new InetOrgPersonContextMapper())
			.userSearchFilter("(uid={0})")
			.userSearchBase("dc=example,dc=com")
			.groupSearchBase("ou=mathematicians,dc=example,dc=com")
			.groupSearchFilter("cn={0}")
			.contextSource().url("ldap://ldap.forumsys.com")
			.port(389)
			.managerDn("cn=read-only-admin,dc=example,dc=com")
			.managerPassword("password");
	}
}