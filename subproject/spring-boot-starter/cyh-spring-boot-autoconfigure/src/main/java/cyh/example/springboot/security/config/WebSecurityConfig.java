package cyh.example.springboot.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.HandlerExceptionResolver;

import lombok.RequiredArgsConstructor;

import cyh.example.springboot.security.handler.JwtAuthenticationEntryPoint;
import cyh.example.springsecurity.jwt.JWTConfigurer;
import cyh.example.springsecurity.jwt.JwtAuthTokenProvider;
import cyh.example.springboot.security.handler.JwtAccessDeniedHandler;

@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final HandlerExceptionResolver handlerExceptionResolver;

    @Value("${jwt.base64-secret}")
    private String base64Secret;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()

                .exceptionHandling()
                .authenticationEntryPoint(new JwtAuthenticationEntryPoint(handlerExceptionResolver))
                .accessDeniedHandler(new JwtAccessDeniedHandler(handlerExceptionResolver))

                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .antMatchers("/auth/token/login").permitAll()
                .antMatchers("/auth/token/refresh").permitAll()
                // .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
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

    @Bean
    public static PasswordEncoder passwordEncoder() {
        // Spring5부터 PasswordEncoder 지정은 필수로 진행해주어야 합니다. 
        // return PasswordEncoderFactories.createDelegatingPasswordEncoder();

        DelegatingPasswordEncoder delegatingPasswordEncoder = (DelegatingPasswordEncoder) PasswordEncoderFactories.createDelegatingPasswordEncoder();
        delegatingPasswordEncoder.setDefaultPasswordEncoderForMatches(NoOpPasswordEncoder.getInstance());
        
        return delegatingPasswordEncoder;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin(CorsConfiguration.ALL);
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
        corsConfiguration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

}
