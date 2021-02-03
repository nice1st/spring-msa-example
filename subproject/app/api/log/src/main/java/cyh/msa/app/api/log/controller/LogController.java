package cyh.msa.app.api.log.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cyh.msa.core.response.CommonResponse;

@RestController
public class LogController {
    
    @GetMapping("/")
    public CommonResponse getLogs() {
        
        return CommonResponse.builder()
        .code("OK")
        .status(200)
        .message("log list")
        .build();
    }
}
