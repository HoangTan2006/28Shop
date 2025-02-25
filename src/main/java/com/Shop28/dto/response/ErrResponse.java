package com.Shop28.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ErrResponse {
    private Integer status;
    private String message;
}
