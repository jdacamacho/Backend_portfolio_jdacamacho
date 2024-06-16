package com.jdacamacho.hexagonal.Domain.UserCases;

import java.util.Date;

import org.springframework.security.authentication.BadCredentialsException;

import com.jdacamacho.hexagonal.Application.Input.ManageAuthCUIntPort;
import com.jdacamacho.hexagonal.Application.Output.ExceptionFormatterIntPort;
import com.jdacamacho.hexagonal.Application.Output.ManageAuthenticatorGatewayIntPort;
import com.jdacamacho.hexagonal.Domain.Objects.User;
import com.jdacamacho.hexagonal.Domain.Objects.Security.Credential;
import com.jdacamacho.hexagonal.Domain.Objects.Security.UserAuth;


public class ManageAuthCUImplAdapter implements ManageAuthCUIntPort{
    private final ManageAuthenticatorGatewayIntPort gatewayAuthenticator;
    private final ExceptionFormatterIntPort exceptionFormatter;

    public ManageAuthCUImplAdapter(ManageAuthenticatorGatewayIntPort gatewayAuthenticator,
                                    ExceptionFormatterIntPort exceptionFormatter){
        this.gatewayAuthenticator = gatewayAuthenticator;
        this.exceptionFormatter = exceptionFormatter;
    }

    @Override
    public UserAuth login(Credential credential) {
        
        if(!this.gatewayAuthenticator.existsByUsername(credential.getUsername())){
            this.exceptionFormatter.responseBadCredentials("Username was not found...");
        }

        String token = "";

        try{
            token = this.gatewayAuthenticator.authenticateUser(credential.getUsername(), credential.getPassword());
        }catch(BadCredentialsException e){
            this.exceptionFormatter.responseBadCredentials("Checkout your username or password, try again please...");
        }

        User user = this.gatewayAuthenticator.findByUsername(credential.getUsername());
    
        UserAuth userAuth = UserAuth.builder()
                            .objUser(user)
                            .token(token)
                            .authenticatedAt(new Date())
                            .build();

        return userAuth;
    }


}
