package com.jdacamacho.hexagonal.Infrastucture.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.jdacamacho.hexagonal.Application.Output.ExceptionFormatterIntPort;
import com.jdacamacho.hexagonal.Application.Output.ManageAuthenticatorGatewayIntPort;
import com.jdacamacho.hexagonal.Application.Output.ManageOwnerGatewayIntPort;
import com.jdacamacho.hexagonal.Application.Output.ManageReservationGatewayIntPort;
import com.jdacamacho.hexagonal.Application.Output.ManageRolegatewayIntPort;
import com.jdacamacho.hexagonal.Application.Output.ManageScheduleGatewayIntPort;
import com.jdacamacho.hexagonal.Application.Output.ManageUserGatewayIntPort;
import com.jdacamacho.hexagonal.Domain.UserCases.ManageAuthCUImplAdapter;
import com.jdacamacho.hexagonal.Domain.UserCases.ManageFieldCUImplAdapter;
import com.jdacamacho.hexagonal.Domain.UserCases.ManageOwnerCUImplAdapter;
import com.jdacamacho.hexagonal.Domain.UserCases.ManageReservationCUImplAdapter;
import com.jdacamacho.hexagonal.Domain.UserCases.ManageUserCUImplAdapter;
import com.jdacamacho.hexagonal.Infrastucture.Input.ErrorCatcher;
import com.jdacamacho.hexagonal.Infrastucture.Output.Persistence.Gateway.ManageFieldGatewayImplAdapter;

@Configuration
public class BeanConfigurations {
    
    @Bean
    public ManageUserCUImplAdapter createUserCU(ManageUserGatewayIntPort gatewayUser,
                                                ManageRolegatewayIntPort gatewayRole,
                                                PasswordEncoder passwordEncoder,
                                                ExceptionFormatterIntPort exceptionFormatter){
        return new ManageUserCUImplAdapter(gatewayUser, gatewayRole, passwordEncoder,exceptionFormatter);
    }

    @Bean
    public ManageOwnerCUImplAdapter createOwnerCU(ManageOwnerGatewayIntPort gatewayOwner,
                                                ManageUserGatewayIntPort gatewayUser,
                                                ManageRolegatewayIntPort gatewayRole,
                                                PasswordEncoder passwordEncoder,
                                                ExceptionFormatterIntPort exceptionFormatter){
        return new ManageOwnerCUImplAdapter(gatewayOwner,gatewayUser ,gatewayRole, passwordEncoder, exceptionFormatter);
    }

    @Bean
    public ManageFieldCUImplAdapter createFieldCU(ManageFieldGatewayImplAdapter gatewayField,
                                                ManageOwnerGatewayIntPort gatewayOwner,
                                                ExceptionFormatterIntPort exceptionFormatter){
        return new ManageFieldCUImplAdapter(gatewayField, gatewayOwner, exceptionFormatter);
    }

    @Bean
    public ManageReservationCUImplAdapter createReservationCU(ManageReservationGatewayIntPort gatewayReservation,
                                                            ManageUserGatewayIntPort gatewayUser,
                                                            ManageFieldGatewayImplAdapter gatewayField,
                                                            ManageScheduleGatewayIntPort gatewaySchedule,
                                                            ExceptionFormatterIntPort exceptionFormatter){
        return new ManageReservationCUImplAdapter(gatewayReservation, gatewayUser, gatewaySchedule, gatewayField, exceptionFormatter);
    }

    @Bean
    public ManageAuthCUImplAdapter createAuthCU(ManageAuthenticatorGatewayIntPort gatewayAuthenticator,
                                                ExceptionFormatterIntPort exceptionFormatter){
        return new ManageAuthCUImplAdapter(gatewayAuthenticator, exceptionFormatter);
    }

    @Bean
    public ErrorCatcher createErrorCatcher(){
        return new ErrorCatcher();
    }
}
