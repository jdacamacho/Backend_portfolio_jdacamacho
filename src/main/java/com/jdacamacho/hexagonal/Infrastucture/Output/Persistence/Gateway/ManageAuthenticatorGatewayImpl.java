package com.jdacamacho.hexagonal.Infrastucture.Output.Persistence.Gateway;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.jdacamacho.hexagonal.Application.Output.ManageAuthenticatorGatewayIntPort;
import com.jdacamacho.hexagonal.Domain.Objects.User;
import com.jdacamacho.hexagonal.Infrastucture.JWT.JwtService;
import com.jdacamacho.hexagonal.Infrastucture.Output.Persistence.Entities.UserEntity;
import com.jdacamacho.hexagonal.Infrastucture.Output.Persistence.Repositories.UserRepository;

@Service
public class ManageAuthenticatorGatewayImpl implements ManageAuthenticatorGatewayIntPort{
    private final UserRepository serviceDB;
    private final JwtService jwtService;
    private final ModelMapper mapper;
    private final AuthenticationManager authenticationManager;

    public ManageAuthenticatorGatewayImpl(UserRepository serviceBD,
                                        JwtService jwtService,
                                        ModelMapper mapper,
                                        AuthenticationManager ayAuthenticationManager){
        this.serviceDB = serviceBD;
        this.jwtService = jwtService;
        this.mapper = mapper;
        this.authenticationManager = ayAuthenticationManager;
    }

    @Override
    public boolean authenticationIsValid(String username, String password) {
        return this.serviceDB.existsByUsernameAndPassword(username, password);
    }

    @Override
    public String authenticateUser(String username, String password) {
        
        String  token = "";
        Optional<UserEntity> userBD = this.serviceDB.findByUsername(username);

        Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        if(authentication.isAuthenticated()){

            UserDetails user = userBD.get();
            token = this.jwtService.getToken(user);

        }

        return token;
    }

    @Override
    public User findByUsername(String username) {
        UserEntity data = this.serviceDB.findByUsername(username).get();
        return this.mapper.map(data, User.class);
    }


}
