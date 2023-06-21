package com.loanpro.challengebe.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserAuthResponseDTO {

    private String username;
    private String accessToken;
}
