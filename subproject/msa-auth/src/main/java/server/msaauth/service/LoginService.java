package server.msaauth.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import cyh.core.security.provider.JwtAuthToken;
import cyh.core.security.provider.JwtAuthTokenProvider;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtAuthTokenProvider jwtAuthTokenProvider;

    public Optional<UserDetails> login(String id, String password) {

        UsernamePasswordAuthenticationToken authenticationToken;
        try {
            authenticationToken = new UsernamePasswordAuthenticationToken(id, password);
        } catch (Exception e) {
            log.info("UsernamePasswordAuthenticationToken :: {}", e.getMessage());
            e.printStackTrace();
            throw e;
        }
        
        //사용자 비밀번호 체크, 패스워드 일치하지 않는다면 Exception 발생 및 이후 로직 실행 안됨
        Authentication authentication;
        try {
            authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        } catch (Exception e) {
            log.info("authenticate :: {}", e.getMessage());
            e.printStackTrace();
            throw e;
        }

        //로그인 성공하면 인증 객체 생성 및 스프링 시큐리티 설정
        // SecurityContextHolder.getContext().setAuthentication(authentication);

        return Optional.ofNullable((UserDetails) authentication.getPrincipal());
    }

    public JwtAuthToken createAuthToken(UserDetails user, long LOGIN_RETENTION_MINUTES) {
        Date expiredDate = Date.from(LocalDateTime.now().plusMinutes(LOGIN_RETENTION_MINUTES).atZone(ZoneId.systemDefault()).toInstant());
        String[] roles = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()).toArray(new String[0]);
        return jwtAuthTokenProvider.createAuthToken(user.getUsername(), roles, expiredDate);
    }

    public JwtAuthToken convertAuthToken(String authToken) {
        return jwtAuthTokenProvider.convertAuthToken(authToken);
    }

    public UserDetails getUserInformationByToken(JwtAuthToken authToken) {
        Authentication authentication = jwtAuthTokenProvider.getAuthentication(authToken);
        return (UserDetails) authentication.getPrincipal();
    }
}