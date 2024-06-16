package com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageLogin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdacamacho.hexagonal.Application.Input.ManageAuthCUIntPort;
import com.jdacamacho.hexagonal.Domain.Objects.Security.Credential;
import com.jdacamacho.hexagonal.Infrastucture.Input.ErrorCatcher;
import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageLogin.DTORequest.CredentialDTORequest;
import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageLogin.DTOResponse.UserAuthDTOResponse;
import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageLogin.Mappers.MapperAuthInfrastructureDomainImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@Validated
@RequiredArgsConstructor
public class AuthRestController {
    private final ManageAuthCUIntPort authCU;
    private final MapperAuthInfrastructureDomainImpl mapper;
    private final ErrorCatcher errorCatcher;

    @PostMapping("")
    public ResponseEntity<?> login(@Valid @RequestBody CredentialDTORequest request, BindingResult result){
        Credential credential = this.mapper.mapRequestToModel(request);

        Map<String, Object> response = new HashMap<>();
        response = this.errorCatcher.catchErrors(result);
        
        if(response.size() != 0){
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
        }

        try{
            UserAuthDTOResponse objUser = this.mapper.mapModelToResponse(this.authCU.login(credential) );
            return new ResponseEntity<UserAuthDTOResponse>(objUser, HttpStatus.CREATED);
        }catch(DataAccessException e){
            response.put("message", "Error of database");
            response.put("error", e.getMessage() + "" + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
