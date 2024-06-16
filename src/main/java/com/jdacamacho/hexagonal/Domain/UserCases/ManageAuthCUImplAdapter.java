package com.jdacamacho.hexagonal.Domain.UserCases;

import java.util.Date;

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
        
        if(!this.gatewayAuthenticator.authenticationIsValid(credential.getUsername(), credential.getPassword())){
            this.exceptionFormatter.responseBadCredentials("Checkout your credentials...");
        }

        String token = this.gatewayAuthenticator.authenticateUser(credential.getUsername(), credential.getPassword());

        if(token.isBlank()){
            this.exceptionFormatter.responseBadCredentials("Has occurred an error, try again please...");
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
