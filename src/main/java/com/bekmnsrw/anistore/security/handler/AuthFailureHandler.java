package com.bekmnsrw.anistore.security.handler;

import com.bekmnsrw.anistore.dto.UserDto;
import com.bekmnsrw.anistore.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthFailureHandler implements AuthenticationFailureHandler {

    private final UserService userService;

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception
    ) throws IOException {
        String email = request.getParameter("email");
        UserDto user = userService.findByEmail(email);

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/sign-in?email=" + email + "&error=wrong-email");
        } else {
            response.sendRedirect(request.getContextPath() + "/sign-in?email=" + email + "&error=wrong-password");
        }
    }
}
