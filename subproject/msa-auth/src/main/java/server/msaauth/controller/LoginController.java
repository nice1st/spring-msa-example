package server.msaauth.controller;

import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import cyh.core.response.CommonResponse;
import cyh.core.security.provider.JwtAuthToken;
import cyh.core.exception.LoginFailedException;
import server.msaauth.security.entity.UserInformation;
import server.msaauth.service.LoginService;
import server.msaauth.dto.LoginRequestDTO;
// import server.msaauth.security.provider.JwtAuthToken;

@Slf4j
@RestController
@RequestMapping("/auth/token")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public CommonResponse login(HttpServletResponse response, @RequestBody LoginRequestDTO loginRequestDTO) {
        Optional<UserInformation> optionalUser = loginService.login(loginRequestDTO.getId(), loginRequestDTO.getPassword());

        if (optionalUser.isPresent()) {
            JwtAuthToken refreshToken = (JwtAuthToken) loginService.createAuthToken(optionalUser.get(), 60L);
            Cookie cookie = new Cookie("refreshToken", refreshToken.getToken());
            cookie.setMaxAge(60 * 60);
            cookie.setPath("/");
            response.addCookie(cookie);
            
            JwtAuthToken accessToken = (JwtAuthToken) loginService.createAuthToken(optionalUser.get(), 30L);
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
    public CommonResponse login(@CookieValue(name = "refreshToken", required = false) String reqRefreshToken) {
            // 쿠키
            log.info(reqRefreshToken);

            return CommonResponse.builder()
                    .code("SUCCESS")
                    .status(200)
                    .message("")
                    .build();
    }
}