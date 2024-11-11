package com.myblog2.Security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                authException.getMessage());
//        it validates and checks the incoming HTTP request  if thet HTTp request happens to be unauthorized access is happening this class will thorw an exception.
//        it read url and send the response to user that this url is unauthorized.the user is not authenticated or lacks the necessary permissions, Spring Security triggers an authrntication exception
    }
}
