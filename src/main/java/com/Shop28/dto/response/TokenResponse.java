package com.Shop28.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class TokenResponse {
    private Integer id;
    private String firstName;
    private String lastName;
    private String accessToken;
    private String refreshToken;
}
