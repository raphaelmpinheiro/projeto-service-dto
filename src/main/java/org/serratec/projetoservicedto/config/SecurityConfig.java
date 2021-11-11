package org.serratec.projetoservicedto.config;

import org.serratec.projetoservicedto.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {		
		auth.userDetailsService(customUserDetailsService)
		.passwordEncoder(passwordEncoder());	
	}
	
	
	
// Teoricamente o método está obsoleto.
	//Método para criação dos usuários.
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//		.withUser("raphael").password(passwordEncoder().encode("123456")).roles("usuario")
//		.and()
//		.withUser("admin").password(passwordEncoder().encode("321")).roles("admin", "usuario")
//		//role = admin -> ROLE_admin
//		.and()
//		.withUser("master").password(passwordEncoder().encode("abc")).roles("admin");	
//
//	}
		
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic()
		.and()
		.authorizeRequests()		
		// /api/usuario/ - ok
		// /servico/usuario/ - ok
		// /api/usuario/1/todos - falha
		// /api/pessoas/usuario/1 - ok					
		// /*/categoria/**
		
//		.antMatchers(HttpMethod.POST, "/api/**").anonymous()
//		.antMatchers(HttpMethod.GET, "/*/categoria/**").anonymous()
//		.antMatchers(HttpMethod.PUT, "/*/categoria/**").authenticated()
//		.antMatchers(HttpMethod.DELETE, "/*/categoria/**").authenticated()
		
		.antMatchers( "/h2-console/**").anonymous()		
		.antMatchers(HttpMethod.POST, "/*/usuario/**").anonymous()
		.antMatchers( "/*/usuario/**").authenticated()
		.antMatchers("/*/cep/**").authenticated()
		//.anyRequest().authenticated()		
		.and()
		.csrf().disable() // https://owasp.org/www-community/attacks/csrf#
		.formLogin().disable()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		//desabilitamos a session REST não necessita e pode gerar problemas na autenticação.
		
		//Toda a aplicação esteja dentro da mesma URL.
		
		//.antMatchers("/swagger/**").permitAll();
		//.antMatchers("/*/usuario/**)").anonymous();
		// url /api/usuario/get , /xpto/usuario/post
	
	}
	
	//Definir o Enconder de criptografia da aplicação.
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
