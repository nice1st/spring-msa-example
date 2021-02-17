package cyh.example.springboot.security.handler;

import lombok.extern.slf4j.Slf4j;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    private HandlerExceptionResolver handlerExceptionResolver;

    public JwtAccessDeniedHandler(HandlerExceptionResolver handlerExceptionResolver) {
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {

        //response.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage());

        //동작하지 않음!!
        //throw new CustomAuthenticationException();
        log.info("JwtAccessDeniedHandler");
        handlerExceptionResolver.resolveException(request, response, null, accessDeniedException);
    }
}
