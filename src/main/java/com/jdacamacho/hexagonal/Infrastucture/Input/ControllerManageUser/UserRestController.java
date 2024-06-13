package com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdacamacho.hexagonal.Application.Input.ManageUserCUIntPort;
import com.jdacamacho.hexagonal.Domain.Models.User;
import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageUser.DTORequest.UserDTORequest;
import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageUser.DTOResponse.UserDTOResponse;
import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageUser.MapperInfrastuctureDomain.MapperUserInfrasctuctureDomain;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@Validated
@RequiredArgsConstructor
public class UserRestController {
    private final ManageUserCUIntPort userCU;
    private final MapperUserInfrasctuctureDomain mapper;

    @GetMapping("")
    public ResponseEntity<List<UserDTOResponse>> list(){
        List<User> users = this.userCU.listUsers();
        ResponseEntity<List<UserDTOResponse>> objResponse = new ResponseEntity<List<UserDTOResponse>>(
            mapper.mapModelsToResponse(users), HttpStatus.OK  
        );
        return objResponse;
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<UserDTOResponse> get(@PathVariable long idUser){
        User user = this.userCU.findById(idUser);
        ResponseEntity<UserDTOResponse> objResponse = new ResponseEntity<UserDTOResponse>(
            mapper.mapModelToResponse(user), HttpStatus.OK);
        return objResponse;
    }

    @PostMapping("")
    public ResponseEntity<?> save(@Valid @RequestBody UserDTORequest request , BindingResult result){
        User user = this.mapper.mapRequestToModel(request);
        Map<String, Object> response = new HashMap<>();
        UserDTOResponse objUser;

        if (result.hasErrors()) {
            List<String> listaErrores = new ArrayList<>();

            for (FieldError error : result.getFieldErrors()) {
                listaErrores.add("The field '" + error.getField() + "‘ " + error.getDefaultMessage());
            }

            response.put("errors", listaErrores);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            objUser = this.mapper.mapModelToResponse(this.userCU.saveUser(user));
        } catch (DataAccessException e) {
            response.put("mensaje", "Error when inserting into database");
            response.put("error", e.getMessage() + "" + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<UserDTOResponse>(objUser, HttpStatus.CREATED);
    }

    @PutMapping("/{idUser}")
    public ResponseEntity<?> update(@Valid @RequestBody UserDTORequest request , @PathVariable long idUser, BindingResult result){
        User user = this.mapper.mapRequestToModel(request);
        Map<String, Object> response = new HashMap<>();
        UserDTOResponse objUser;

        if (result.hasErrors()) {
            List<String> listaErrores = new ArrayList<>();

            for (FieldError error : result.getFieldErrors()) {
                listaErrores.add("The field '" + error.getField() + "‘ " + error.getDefaultMessage());
            }

            response.put("errors", listaErrores);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            objUser = this.mapper.mapModelToResponse(this.userCU.updateUser(idUser, user));
        } catch (DataAccessException e) {
            response.put("mensaje", "Error when inserting into database");
            response.put("error", e.getMessage() + "" + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<UserDTOResponse>(objUser, HttpStatus.OK);
    }
}
