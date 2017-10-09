package com.intuit.intuitter;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.userdetails.InetOrgPersonContextMapper;

@Configuration
@EnableGlobalAuthentication
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.anyRequest()
			.fullyAuthenticated()
			.and()
			.formLogin();
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
		 
//		auth
//			.ldapAuthentication()
//				.userDnPatterns("dn: uid={0},ou=people,dc=intuit,dc=com")
//				.groupSearchBase("ou=groups")
//				.contextSource()
//					.url("ldap://localhost:8389/dc=intuit,dc=com")
//					.and()
//				.passwordCompare()
//					.passwordEncoder(new LdapShaPasswordEncoder())
//					.passwordAttribute("userPassword");
	}
}