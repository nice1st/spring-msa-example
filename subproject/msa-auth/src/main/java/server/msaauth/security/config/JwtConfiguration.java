package server.msaauth.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cyh.core.security.provider.JwtAuthTokenProvider;
// import server.msaauth.security.provider.JwtAuthTokenProvider;

/**
 * jwt key : 각 프로젝트에서 bean 생성
 */
@Configuration
public class JwtConfiguration {

    @Value("${jwt.base64-secret}")
    private String base64Secret;

    @Bean
    public JwtAuthTokenProvider jwtProvider() {
        return new JwtAuthTokenProvider(base64Secret);
    }
}