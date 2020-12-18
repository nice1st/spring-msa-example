package server.msaauth.controller;

import server.msaauth.core.CommonResponse;
import server.msaauth.exception.LoginFailedException;
import server.msaauth.security.entity.UserInformation;
import server.msaauth.security.provider.JwtAuthToken;
import server.msaauth.service.LoginService;
import server.msaauth.dto.LoginRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth/token")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public CommonResponse login(@RequestBody LoginRequestDTO loginRequestDTO) {

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