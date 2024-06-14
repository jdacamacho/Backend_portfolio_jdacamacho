package com.jdacamacho.hexagonal.Infrastucture.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jdacamacho.hexagonal.Application.Output.ExceptionFormatterIntPort;
import com.jdacamacho.hexagonal.Application.Output.ManageOwnerGatewayIntPort;
import com.jdacamacho.hexagonal.Application.Output.ManageRolegatewayIntPort;
import com.jdacamacho.hexagonal.Application.Output.ManageUserGatewayIntPort;
import com.jdacamacho.hexagonal.Domain.UserCases.ManageOwnerCUImplAdapter;
import com.jdacamacho.hexagonal.Domain.UserCases.ManageUserCUImplAdapter;
import com.jdacamacho.hexagonal.Infrastucture.Input.ErrorCatcher;

@Configuration
public class BeanConfigurations {
    
    @Bean
    public ManageUserCUImplAdapter createUserCU(ManageUserGatewayIntPort gatewayUser,
                                                ManageRolegatewayIntPort gatewayRole,
                                                ExceptionFormatterIntPort exceptionFormatter){
        return new ManageUserCUImplAdapter(gatewayUser, gatewayRole, exceptionFormatter);
    }

    @Bean
    public ManageOwnerCUImplAdapter createOwnerCU(ManageOwnerGatewayIntPort gatewayOwner,
                                                ManageUserGatewayIntPort gatewayUser,
                                                ManageRolegatewayIntPort gatewayRole,
                                                ExceptionFormatterIntPort exceptionFormatter){
        return new ManageOwnerCUImplAdapter(gatewayOwner,gatewayUser ,gatewayRole, exceptionFormatter);
    }

    @Bean
    public ErrorCatcher createErrorCatcher(){
        return new ErrorCatcher();
    }
}
