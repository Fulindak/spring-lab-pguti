package com.example.springkuzmin.service.user;


import com.example.springkuzmin.component.JwtUserIdTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenParserFromRequest {
    private final JwtUserIdTokenUtil jwtUserIdTokenUtil;
    @Autowired
    public TokenParserFromRequest(JwtUserIdTokenUtil jwtUserIdTokenUtil) {
        this.jwtUserIdTokenUtil = jwtUserIdTokenUtil;
    }
    public String parse(HttpServletRequest request){
        final String requestTokenHeader = request.getHeader("Authorization");
        if (requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Authorization token not found");
        }
        return requestTokenHeader.split(" ")[1];
    }
}

