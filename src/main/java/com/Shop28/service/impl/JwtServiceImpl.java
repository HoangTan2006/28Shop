package com.Shop28.service.impl;

import com.Shop28.service.JwtService;
import com.Shop28.service.UserService;
import com.Shop28.util.TypeToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final UserService userService;

    @Value("${jwt.accessSecretKey}")
    private String ACCESS_KEY;

    @Value("${jwt.refreshSecretKey}")
    private String REFRESH_KEY;

    @Value("${jwt.expire.accessToken}")
    private Long EXPIRE_ACCESS_TOKEN;

    @Value("${jwt.expire.refreshToken}")
    private Long EXPIRE_REFRESH_TOKEN;

    @Override
    public String generateToken(String username, TypeToken typeToken) {
        UserDetails userDetails = userService.findByUsername(username);
        return Jwts.builder()
                .subject(username)
                .issuer("HoangTan")
                .claim("role", userDetails.getAuthorities())
                .issuedAt(new Date())
                .expiration(new Date(getExpire(typeToken)))
                .signWith(getKey(typeToken))
                .compact();
    }

    @Override
    public Boolean verifyToken(String token, TypeToken typeToken) {
        Date expiration = extractAllClaim(token, typeToken).getExpiration();
        return expiration.after(new Date());
    }

    @Override
    public Claims extractAllClaim(String token, TypeToken typeToken) {
        return Jwts.parser()
                .verifyWith(getKey(typeToken))
                .build()
                .parseClaimsJws(token)
                .getPayload();
    }

    @Override
    public String extractUsername(String token, TypeToken typeToken) {
        return "";
    }

    private SecretKey getKey(TypeToken typeToken) {
        if (typeToken.equals(TypeToken.ACCESS_TOKEN)) return Keys.hmacShaKeyFor(ACCESS_KEY.getBytes());
        else return Keys.hmacShaKeyFor(REFRESH_KEY.getBytes());
    }

    private Long getExpire(TypeToken typeToken) {
        if (typeToken.equals(TypeToken.ACCESS_TOKEN)) return EXPIRE_ACCESS_TOKEN;
        else return EXPIRE_REFRESH_TOKEN;
    }
}
