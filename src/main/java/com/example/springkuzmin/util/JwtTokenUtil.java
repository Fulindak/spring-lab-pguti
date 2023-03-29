package com.example.springkuzmin.util;

import io.jsonwebtoken.Claims;

import java.util.Date;
import java.util.function.Function;


public interface JwtTokenUtil<S> {
    boolean validteToken(String token, S subject);
    S getSubjectFromToken(String token);
    Date getExpirationDateFromToken(String token);
    <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver);
    String generateToken(S subject);

    boolean validateToken(String token, S id);
}
