package server.msaauth.controller;

import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import cyh.core.response.CommonResponse;
import cyh.core.security.provider.JwtAuthToken;
import cyh.core.exception.LoginFailedException;
import cyh.core.exception.TokenValidFailedException;
import server.msaauth.service.LoginService;
import server.msaauth.dto.LoginRequestDTO;

@Slf4j
@RestController
@RequestMapping("/auth/token")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    private void addTokenToCookie(HttpServletResponse response, JwtAuthToken token) {
        Cookie cookie = new Cookie("refreshToken", token.getToken());
        cookie.setMaxAge(60 * 60);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    @PostMapping("/login")
    public CommonResponse login(HttpServletResponse response, @RequestBody LoginRequestDTO loginRequestDTO) {
        Optional<UserDetails> optionalUser = loginService.login(loginRequestDTO.getId(), loginRequestDTO.getPassword());

        if (optionalUser.isPresent()) {
            JwtAuthToken refreshToken = loginService.createAuthToken(optionalUser.get(), 60L);
            JwtAuthToken accessToken = loginService.createAuthToken(optionalUser.get(), 30L);
            
            addTokenToCookie(response, refreshToken);
            return CommonResponse.builder()
                    .code("LOGIN_SUCCESS")
                    .status(200)
                    .message(accessToken.getToken())
                    .build();

        } else {
            throw new LoginFailedException();
        }
    }

    @PostMapping("/refresh")
    public CommonResponse refresh(HttpServletResponse response, @CookieValue(name = "refreshToken", required = false) String reqRefreshToken) {
        if (reqRefreshToken == null || reqRefreshToken.isEmpty()) {
            throw new TokenValidFailedException();
        }
        // 쿠키
        JwtAuthToken jwtAuthToken = loginService.convertAuthToken(reqRefreshToken);
        if(!jwtAuthToken.validate()) {
            throw new TokenValidFailedException();
        }
        UserDetails user = loginService.getUserInformationByToken(jwtAuthToken);
        // user.getAuthorities().stream().forEach((authority) -> {
        //     log.info(authority.getAuthority());
        // });

        JwtAuthToken refreshToken = loginService.createAuthToken(user, 60L);
        JwtAuthToken accessToken = loginService.createAuthToken(user, 30L);
        
        addTokenToCookie(response, refreshToken);
        return CommonResponse.builder()
                .code("REFRESH_SUCCESS")
                .status(200)
                .message(accessToken.getToken())
                .build();
    }
}