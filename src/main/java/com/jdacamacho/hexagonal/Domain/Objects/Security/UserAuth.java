package com.jdacamacho.hexagonal.Domain.Objects.Security;

import java.util.Date;

import com.jdacamacho.hexagonal.Domain.Objects.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserAuth {
    private User objUser;
    private String token;
    private Date authenticatedAt;

    public UserAuth(){

    }
}
