package com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageUser;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdacamacho.hexagonal.Application.Input.ManageUserCUIntPort;
import com.jdacamacho.hexagonal.Domain.Objects.User;
import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageUser.DTOResponse.UserDTOResponse;
import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageUser.mappers.MapperUserInfrastuctureDomainInt;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@Validated
@RequiredArgsConstructor
public class UserRestController {
    private final ManageUserCUIntPort userCU;
    private final MapperUserInfrastuctureDomainInt mapper;

    @GetMapping("")
    @Transactional(readOnly = true)
    public ResponseEntity<List<UserDTOResponse>> index(){
        List<User> users = this.userCU.listUsers();
        return new ResponseEntity<>(
            this.mapper.mapModelToResponse(users), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<UserDTOResponse> getUser(@PathVariable long id){
        User user = this.userCU.findUserById(id);
        return new ResponseEntity<>(
            this.mapper.mapModelToResponse(user),HttpStatus.OK);
    }



}
