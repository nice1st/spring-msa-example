package cyh.core.security.provider;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SecurityException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

@Slf4j
public class JwtAuthToken {

    @Getter
    private final String token;
    private final Key key;

    private static final String AUTHORITIES_KEY = "role";

    JwtAuthToken(String token, Key key) {
        this.token = token;
        this.key = key;
    }

    JwtAuthToken(String id, String[] roles, Date expiredDate, Key key) {
        this.key = key;
        this.token = createJwtAuthToken(id, roles, expiredDate).get();
    }

    public boolean validate() {
        return getData() != null;
    }

    public Claims getData() {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        } catch (SecurityException e) {
            log.info("Invalid JWT signature.");
        } catch (MalformedJwtException e) {
            log.info("Invalid JWT token.");
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token.");
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token.");
        } catch (IllegalArgumentException e) {
            log.info("JWT token compact of handler are invalid.");
        }
        return null;
    }

    private Optional<String> createJwtAuthToken(String id, String[] roles, Date expiredDate) {

        var token = Jwts.builder()
                .setSubject(id)
                .claim(AUTHORITIES_KEY, roles)
                .signWith(this.key, SignatureAlgorithm.HS256)
                .setExpiration(expiredDate)
                .compact();

        return Optional.ofNullable(token);
    }
}
