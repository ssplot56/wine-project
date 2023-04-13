package com.project.wineshop.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAuthResponse {
    private String token;
    private String tokenType = "Bearer";
}
