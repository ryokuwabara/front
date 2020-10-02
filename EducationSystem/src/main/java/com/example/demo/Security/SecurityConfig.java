package com.example.demo.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private SuccessHandler successHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.formLogin() // ログインページに飛ばす
				.loginPage("/login") // ログイン画面
				.loginProcessingUrl("/login").usernameParameter("username").passwordParameter("password")
				.successHandler(successHandler).failureUrl("/login-error") // ログイン失敗時の画面遷移
				.permitAll();
		http.authorizeRequests()
				.antMatchers("/css/*").permitAll()
				.antMatchers("/js/*").permitAll()
				.antMatchers("/tutor/**").hasAnyRole("TUTOR", "ADMIN")
				.antMatchers("/admin/**","/user-management/**").hasRole("ADMIN")
				.antMatchers("/user/**").hasAnyRole("USER", "TUTOR", "ADMIN")
				.antMatchers("/index/**","/profile/**").hasAnyRole("USER", "TUTOR", "ADMIN","VIEWER").and()
				.exceptionHandling().accessDeniedHandler(new Redirect());


		http.logout().logoutSuccessUrl("/login") // ログアウト機能
				.invalidateHttpSession(true).deleteCookies("JSESSIONID"
				, "SESSION", "remember-me").permitAll();
	}

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	void configureAuthenticationManager(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
