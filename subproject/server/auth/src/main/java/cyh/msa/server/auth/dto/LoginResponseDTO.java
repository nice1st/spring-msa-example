package cyh.msa.server.auth.dto;

import lombok.Data;

@Data
public class LoginResponseDTO {
    
    private String accessToken;
    private String refreshToken;
}
