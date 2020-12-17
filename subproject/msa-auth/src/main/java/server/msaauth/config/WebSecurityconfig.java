package server.msaauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityconfig extends WebSecurityConfigurerAdapter {
    
	private AuthenticationManager authenticationManager;
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		this.authenticationManager = super.authenticationManagerBean();
		return this.authenticationManager;
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.cors().and()
		// .authorizeRequests()
			// .antMatchers(HttpMethod.OPTIONS, "/oauth/token").permitAll()
		;
	}
}