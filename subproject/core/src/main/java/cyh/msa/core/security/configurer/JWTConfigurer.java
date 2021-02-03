package cyh.msa.core.security.configurer;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import cyh.msa.core.security.provider.JwtAuthTokenProvider;
import cyh.msa.core.security.filter.JWTFilter;

public class JWTConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private JwtAuthTokenProvider jwtAuthTokenProvider;

    public JWTConfigurer(JwtAuthTokenProvider jwtAuthTokenProvider) {
        this.jwtAuthTokenProvider = jwtAuthTokenProvider;
    }

    @Override
    public void configure(HttpSecurity http) {
        JWTFilter customFilter = new JWTFilter(jwtAuthTokenProvider);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
