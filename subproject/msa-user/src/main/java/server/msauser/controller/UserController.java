package server.msauser.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cyh.core.response.CommonResponse;
import cyh.domain.entity.User;

@RestController
public class UserController {
    
    @GetMapping("/")
    public CommonResponse getUsers() {
        
        return CommonResponse.builder()
            .code("OK")
            .status(200)
            .message("user list")
            .build();
    }
    
    @GetMapping("/public")
    public CommonResponse postPublic() {
        
        return CommonResponse.builder()
            .code("OK")
            .status(200)
            .message("User public.")
            .build();
    }

    @PostMapping("/user")
    public CommonResponse postUser(@RequestBody User user) {
        
        return CommonResponse.builder()
            .code("OK")
            .status(200)
            .message("User Information.")
            .build();
    }
}