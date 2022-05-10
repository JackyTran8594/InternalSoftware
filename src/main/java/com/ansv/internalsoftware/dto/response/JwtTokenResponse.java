package com.ansv.internalsoftware.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import static com.ansv.internalsoftware.constants.Constants.JWT_AUTH_TOKEN_VALIDITY;

@Data
public class JwtTokenResponse {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("expires_in")
    private long expiresIn = JWT_AUTH_TOKEN_VALIDITY;
}
