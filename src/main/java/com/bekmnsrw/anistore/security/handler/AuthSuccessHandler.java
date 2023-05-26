package com.bekmnsrw.anistore.security.handler;

import com.bekmnsrw.anistore.security.details.UserDetailsImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    private final HttpSession httpSession;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        httpSession.setAttribute("email", userDetails.getUsername());
        httpSession.setAttribute("role", String.valueOf(userDetails.getAuthorities().stream().findFirst().get()));

        response.setStatus(HttpServletResponse.SC_OK);
        response.sendRedirect("profile");
    }
}
