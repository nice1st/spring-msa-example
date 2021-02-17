package cyh.example.springboot.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "cyh.security.jwt")
@Data
public class CyhSecuriryJwtProperties {
    String base64Secret;
}
