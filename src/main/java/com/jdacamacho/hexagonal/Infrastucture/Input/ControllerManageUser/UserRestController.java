package com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdacamacho.hexagonal.Application.Input.ManageUserCUIntPort;
import com.jdacamacho.hexagonal.Domain.Objects.User;
import com.jdacamacho.hexagonal.Infrastucture.Input.ErrorCatcher;
import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageUser.DTORequest.UserDTORequest;
import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageUser.DTORequest.UserUpdateDTORequest;
import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageUser.DTOResponse.UserDTOResponse;
import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageUser.mappers.MapperUserInfrastructureDomainInt;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@Validated
@RequiredArgsConstructor
public class UserRestController {
    private final ManageUserCUIntPort userCU;
    private final MapperUserInfrastructureDomainInt mapper;
    private final ErrorCatcher errorCatcher;

    @GetMapping("/adm")
    @PreAuthorize("hasRole('Administrator')")
    @Transactional(readOnly = true)
    public ResponseEntity<List<UserDTOResponse>> index(){
        List<User> users = this.userCU.listUsers();
        
        return new ResponseEntity<>(
            this.mapper.mapModelToResponse(users), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('Administrator', 'Client')")
    @Transactional(readOnly = true)
    public ResponseEntity<UserDTOResponse> getUser(@PathVariable long id){
        User user = this.userCU.findUserById(id);
        
        return new ResponseEntity<>(
            this.mapper.mapModelToResponse(user),HttpStatus.OK);
    }

    @PostMapping("")
    @Transactional
    public ResponseEntity<?> saveUser(@Valid @RequestBody UserDTORequest request, BindingResult result){
        User user = this.mapper.mapRequestToModel(request);
        
        Map<String, Object> response = new HashMap<>();
        response = this.errorCatcher.catchErrors(result);
        
        if(response.size() != 0){
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
        }

        try{
            UserDTOResponse objUser = this.mapper.mapModelToResponse(this.userCU.saveUser(user));
            return new ResponseEntity<UserDTOResponse>(objUser, HttpStatus.CREATED);
        }catch(DataAccessException e){
            response.put("message", "Error when inserting into database");
            response.put("error", e.getMessage() + "" + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('Administrator', 'Client')")
    @Transactional
    public ResponseEntity<?> updateUser(@PathVariable long id, @Valid @RequestBody UserUpdateDTORequest request, BindingResult result){
        User user = this.mapper.mapRequestToModel(request);
        
        Map<String, Object> response = new HashMap<>();
        response = this.errorCatcher.catchErrors(result);
        
        if(response.size() != 0){
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
        }

        try{
            UserDTOResponse objUser = this.mapper.mapModelToResponse(this.userCU.updateUser(id, user));
            return new ResponseEntity<UserDTOResponse>(objUser, HttpStatus.OK);
        }catch(DataAccessException e){
            response.put("message", "Error when inserting into database");
            response.put("error", e.getMessage() + "" + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
