package com.Shop28.controller;

import com.Shop28.dto.request.AuthenticationRequest;
import com.Shop28.dto.response.ResponseDTO;
import com.Shop28.dto.response.TokenResponse;
import com.Shop28.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/")
    public ResponseDTO<TokenResponse> auth(@RequestBody AuthenticationRequest authenticationRequest) {
        TokenResponse tokenResponse = authenticationService.authenticate(authenticationRequest);

        return ResponseDTO.<TokenResponse>builder()
                .status(HttpStatus.OK.value())
                .message("Success !")
                .data(tokenResponse)
                .build();
    }
}
