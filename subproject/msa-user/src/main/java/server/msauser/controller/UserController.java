package server.msauser.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cyh.core.response.CommonResponse;
import cyh.domain.entity.User;
import lombok.RequiredArgsConstructor;
import server.msauser.service.UserService;

@RequiredArgsConstructor
@RestController
public class UserController {
    
    private final UserService userService;

    @GetMapping("/user/{id}")
    public CommonResponse getUser(@PathVariable String id) {
        
        return CommonResponse.builder()
            .code("OK")
            .status(200)
            .message(String.format("get user id: %s", id))
            .build();
    }

    @PutMapping("/user/{id}")
    public CommonResponse putUser(@PathVariable String id, @RequestBody User user) {

        return CommonResponse.builder()
            .code("OK")
            .status(200)
            .message(String.format("put user id: %s", id))
            .build();
    }

    @ResponseBody
    @PostMapping("/user")
    public User postUser(@RequestBody User user) {

        return userService.saveUser(user);
    }
    
    @GetMapping("/users")
    public CommonResponse getUsers() {
        
        return CommonResponse.builder()
            .code("OK")
            .status(200)
            .message("user list")
            .build();
    }
}