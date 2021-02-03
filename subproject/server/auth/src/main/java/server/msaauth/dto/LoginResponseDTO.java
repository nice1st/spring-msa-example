package server.msaauth.dto;

import lombok.Data;

@Data
public class LoginResponseDTO {
    
    private String accessToken;
    private String refreshToken;
}
