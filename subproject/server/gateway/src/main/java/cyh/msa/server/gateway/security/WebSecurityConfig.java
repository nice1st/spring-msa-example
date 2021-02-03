package cyh.msa.server.gateway.security;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import cyh.msa.core.security.configurer.JWTConfigurer;
import cyh.msa.core.security.handler.JwtAccessDeniedHandler;
import cyh.msa.core.security.handler.JwtAuthenticationEntryPoint;
import cyh.msa.core.security.provider.JwtAuthTokenProvider;

@EnableAutoConfiguration
@ComponentScan(basePackages = "cyh.core.security")
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Value("${jwt.base64-secret}")
    private String base64Secret;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()

                // .exceptionHandling()
                // .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                // .accessDeniedHandler(jwtAccessDeniedHandler)

                // .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .anyRequest().authenticated()

                .and()
                .cors()

                .and()
                .apply(securityConfigurerAdapter());
    }

    private JWTConfigurer securityConfigurerAdapter() {
        return new JWTConfigurer(jwtProvider());
    }
    
    @Bean
    public JwtAuthTokenProvider jwtProvider() {
        return new JwtAuthTokenProvider(base64Secret);
    }
}
