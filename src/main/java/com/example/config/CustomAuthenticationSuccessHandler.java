package com.example.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        // ログインユーザーのロールを取得
        String role = authentication.getAuthorities().iterator().next().getAuthority();
        if ("COMPANY".equals(role)) {
            response.sendRedirect("/Matching/companyIndex");
        } else {
            response.sendRedirect("/Matching/userIndex");
        }
    }
}