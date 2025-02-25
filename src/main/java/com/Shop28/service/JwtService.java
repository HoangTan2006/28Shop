package com.Shop28.service;

import com.Shop28.util.TypeToken;
import io.jsonwebtoken.Claims;

public interface JwtService {
    String generateToken(String username, TypeToken typeToken);

    Boolean verifyToken(String token, TypeToken typeToken);

    Claims extractAllClaim(String token, TypeToken typeToken);

    String extractUsername(String token, TypeToken typeToken);
}
