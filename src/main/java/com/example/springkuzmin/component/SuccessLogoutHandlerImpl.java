package com.example.springkuzmin.component;

import com.example.springkuzmin.service.user.LogoutService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class SuccessLogoutHandlerImpl implements LogoutSuccessHandler {
    private final LogoutService logoutService;
    @Autowired
    public SuccessLogoutHandlerImpl(LogoutService logoutService) {
        this.logoutService = logoutService;
    }
    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication){
        logoutService.logout(request);
    }
}

