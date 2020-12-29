package server.msaauth.controller;

import java.util.Optional;

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
    public CommonResponse login(@RequestBody LoginRequestDTO loginRequestDTO) {

        log.info("login controller");
        Optional<UserInformation> optionalUser = loginService.login(loginRequestDTO.getId(), loginRequestDTO.getPassword());

        if (optionalUser.isPresent()) {

            JwtAuthToken jwtAuthToken = (JwtAuthToken) loginService.createAuthToken(optionalUser.get());

            return CommonResponse.builder()
                    .code("LOGIN_SUCCESS")
                    .status(200)
                    .message(jwtAuthToken.getToken())
                    .build();

        } else {
            throw new LoginFailedException();
        }
    }
}