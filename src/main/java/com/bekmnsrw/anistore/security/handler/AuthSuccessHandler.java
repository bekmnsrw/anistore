package com.bekmnsrw.anistore.security.handler;

import com.bekmnsrw.anistore.security.details.UserDetailsImpl;
import com.bekmnsrw.anistore.service.CartService;
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
    private final CartService cartService;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String email = userDetails.getUsername();

        httpSession.setAttribute("email", email);
        httpSession.setAttribute("role", String.valueOf(userDetails.getAuthorities().stream().findFirst().get()));
        httpSession.setAttribute("cart", cartService.findCurrentCart(email).getId());

        response.setStatus(HttpServletResponse.SC_OK);
        response.sendRedirect("profile");
    }
}
