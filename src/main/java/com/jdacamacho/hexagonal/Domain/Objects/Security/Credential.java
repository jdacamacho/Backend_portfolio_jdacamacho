package com.jdacamacho.hexagonal.Domain.Objects.Security;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Credential {
    private String username;
    private String password;

    public Credential() {
    
    }
}
