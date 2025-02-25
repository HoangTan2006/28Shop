package com.Shop28.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ResponseDTO<T> {
    private Integer status;
    private String message;
    private T data;
}
