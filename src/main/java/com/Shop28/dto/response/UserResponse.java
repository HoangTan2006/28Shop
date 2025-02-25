package com.Shop28.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UserResponse {
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer phone;
    private String email;
}
