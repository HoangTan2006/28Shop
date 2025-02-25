package com.Shop28.dto.request;

import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class UserRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private Integer phone;
}
