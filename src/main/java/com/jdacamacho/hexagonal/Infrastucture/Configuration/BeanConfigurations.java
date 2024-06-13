package com.jdacamacho.hexagonal.Infrastucture.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jdacamacho.hexagonal.Application.Output.ExceptionFormatterIntPort;
import com.jdacamacho.hexagonal.Application.Output.ManageUserGatewayIntPort;
import com.jdacamacho.hexagonal.Domain.UserCases.ManageUserCUImplAdapter;

@Configuration
public class BeanConfigurations {
    
    @Bean
    public ManageUserCUImplAdapter createUserCU(ManageUserGatewayIntPort gateway,
                                    ExceptionFormatterIntPort exceptionFormatter){
        return new ManageUserCUImplAdapter(gateway, exceptionFormatter);
    }
}
