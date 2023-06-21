package com.loanpro.challengebe.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AuthRequestDTO {

    private String username;
    private String password;
}
