package com.ansv.internalsoftware.dto.response;

import com.ansv.internalsoftware.util.DataUtils;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

import static com.ansv.internalsoftware.constants.Constants.JWT_AUTH_TOKEN_VALIDITY;

@Data
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private boolean success = false;
    private JwtTokenResponse tokenResponse;
    private String username;
    private UserDetails user;
    private List<String> role;
    private List<String> permissions;

    public JwtAuthenticationResponse(String accessToken, UserDetails userDetails) {
        this.accessToken = accessToken;
        JwtTokenResponse tokenResponse = new JwtTokenResponse();
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setExpiresIn(JWT_AUTH_TOKEN_VALIDITY);
        this.success = true;
        this.user = userDetails;
        if (userDetails != null) {
            this.username = userDetails.getUsername();
            if (DataUtils.notNullOrEmpty(userDetails.getAuthorities())) {
                role = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
//                permissions = role;
            }
        }
    }

    public JwtAuthenticationResponse(String accessToken, String username, String role) {
        this.accessToken = accessToken;
        JwtTokenResponse tokenResponse = new JwtTokenResponse();
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setExpiresIn(JWT_AUTH_TOKEN_VALIDITY);
        this.success = true;
        this.username = username;
        this.role = Arrays.asList(role);
        this.permissions = Arrays.asList(role);


    }
}
