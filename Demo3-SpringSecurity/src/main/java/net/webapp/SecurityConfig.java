package net.webapp;

import javax.activation.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)   // securise les methodes
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	
	@Autowired
	public void globalConfig(AuthenticationManagerBuilder auth, javax.sql.DataSource datasource ) throws Exception {
		// origine des users, ldap, en émoire, en base de odnnée, activedirectory etc
		/*
			auth.inMemoryAuthentication().withUser("admin").password("root").roles("ADMIN", "PROF");
			auth.inMemoryAuthentication().withUser("prof").password("root").roles("PROF");
			auth.inMemoryAuthentication().withUser("et1").password("root").roles("ETUDIANT");
			auth.inMemoryAuthentication().withUser("scol").password("root").roles("SCOLARITE");
			*/
		auth.jdbcAuthentication()
			.dataSource(datasource)
			.usersByUsernameQuery("select username as principal, password as credentials, true from users where username = ?")
			.authoritiesByUsernameQuery("select users as principal, roles as role from users_roles where users = ?")
			.rolePrefix("ROLE_");
		
		// TODO
		//		auth.ldapAuthentication()
		
		// TODO facebok twitter
	}
	
	// disable csrf
	// manage sessinon with session id in cookie
	// TODO redirect fail to none permitd accée refusé page page .failureUrl("/error.html")s
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/css/**", "/js/**", "/images/**", "/vendors/**" ).permitAll()
				.anyRequest()
				.authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/index.html")
				.failureUrl("/login")
				.permitAll()
				.and()
			.logout()
				.invalidateHttpSession(true)
				.logoutUrl("/logout")
				.permitAll();
	}
	
	
}
