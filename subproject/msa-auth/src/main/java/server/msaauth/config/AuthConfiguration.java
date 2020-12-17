package server.msaauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import server.msaauth.filter.TokenCookieCreationFilter;
import server.msaauth.service.UserInformationService;

@Configuration
@EnableAuthorizationServer
public class AuthConfiguration extends AuthorizationServerConfigurerAdapter {
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ResourceServerProperties resourceServerProperties;
    
    @Autowired
    private UserInformationService userInformationService;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.addTokenEndpointAuthenticationFilter(new TokenCookieCreationFilter());
        security.addTokenEndpointAuthenticationFilter(new CorsFilter(corsConfigurationSource()));
        security.checkTokenAccess("permitAll()");
	}
	
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 인증 과정 endpoint에 대한 설정을 해줍니다. 
        super.configure(endpoints);
        endpoints
            .accessTokenConverter(this.jwtAccessTokenConverter())
            .userDetailsService(this.userInformationService)
            .authenticationManager(this.authenticationManager)
        ;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
            .withClient("msa_auth_web")
            .authorizedGrantTypes("password", "refresh_token")
            .secret("{noop}websecret")
            .authorities("ROLE_USER")
            .scopes("read", "write")
            .accessTokenValiditySeconds(600) // 10분
            .refreshTokenValiditySeconds(36_000) // 10시간
        .and()
            .withClient("msa_auth_api")
            .authorizedGrantTypes("password", "refresh_token")
            .secret("{noop}apisecret")
            .authorities("ROLE_USER")
            .scopes("read", "write")
            .accessTokenValiditySeconds(3_600)
            .refreshTokenValiditySeconds(360_000)
        ;
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        // JWT key-value 방식을 사용하기 위한 설정입니다.
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        accessTokenConverter.setSigningKey(resourceServerProperties.getJwt().getKeyValue());

        return accessTokenConverter;
    }

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