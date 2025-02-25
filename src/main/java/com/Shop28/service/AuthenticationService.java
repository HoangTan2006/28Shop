package com.Shop28.service;

import com.Shop28.dto.request.AuthenticationRequest;
import com.Shop28.dto.response.TokenResponse;

public interface AuthenticationService {
    TokenResponse authenticate(AuthenticationRequest authenticationRequest);
}
