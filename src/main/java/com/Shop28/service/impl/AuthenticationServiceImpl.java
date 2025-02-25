package com.Shop28.service.impl;

import com.Shop28.dto.request.AuthenticationRequest;
import com.Shop28.dto.response.TokenResponse;
import com.Shop28.entity.User;
import com.Shop28.service.AuthenticationService;
import com.Shop28.service.JwtService;
import com.Shop28.service.UserService;
import com.Shop28.util.TypeToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;

    @Override
    public TokenResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        String accessToken = jwtService.generateToken(authenticationRequest.getUsername(), TypeToken.ACCESS_TOKEN);
        String refreshToken = jwtService.generateToken(authenticationRequest.getUsername(), TypeToken.REFRESH_TOKEN);

        User user = userService.findByUsername(authenticationRequest.getUsername());

        return TokenResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
