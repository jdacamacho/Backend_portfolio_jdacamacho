package com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageOwner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.jdacamacho.hexagonal.Application.Input.ManageOwnerCUIntPort;
import com.jdacamacho.hexagonal.Domain.Objects.Owner;
import com.jdacamacho.hexagonal.Infrastucture.Input.ErrorCatcher;
import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageOwner.DTORequest.OwnerDTORequest;
import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageOwner.DTORequest.OwnerUpdateDTORequest;
import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageOwner.DTOResponse.OwnerDTOResponse;
import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageOwner.mappers.MapperOwnerInfrastructureDomainInt;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/owners")
@Validated
@RequiredArgsConstructor
public class OwnerRestController {
    private final ManageOwnerCUIntPort ownerCU;
    private final MapperOwnerInfrastructureDomainInt mapper;
    private final ErrorCatcher errorCatcher;

    @GetMapping("/adm")
    @Transactional(readOnly = true)
    public ResponseEntity<List<OwnerDTOResponse>> index(){
        List<Owner> owners = this.ownerCU.listOwners();
        
        return new ResponseEntity<>(
            this.mapper.mapModelToResponse(owners), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<OwnerDTOResponse> getOwner(@PathVariable long id){
        Owner owner = this.ownerCU.findOwnerById(id);
        
        return new ResponseEntity<>(
            this.mapper.mapModelToResponse(owner),HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> saveOwner(@Valid @RequestBody OwnerDTORequest request, BindingResult result){
        Owner owner = this.mapper.mapRequestToModel(request);
        
        Map<String, Object> response = new HashMap<>();
        response = this.errorCatcher.catchErrors(result);
        
        if(response.size() != 0){
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
        }

        try{
            OwnerDTOResponse objOwner = this.mapper.mapModelToResponse(this.ownerCU.saverOwner(owner));
            return new ResponseEntity<OwnerDTOResponse>(objOwner, HttpStatus.CREATED);
        }catch(DataAccessException e){
            response.put("message", "Error when inserting into database");
            response.put("error", e.getMessage() + "" + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable long id, @Valid @RequestBody OwnerUpdateDTORequest request, BindingResult result){
        Owner owner = this.mapper.mapRequestToModel(request);
        
        Map<String, Object> response = new HashMap<>();
        response = this.errorCatcher.catchErrors(result);
        
        if(response.size() != 0){
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
        }

        try{
            OwnerDTOResponse objOwner = this.mapper.mapModelToResponse(this.ownerCU.updateOwner(id, owner));
            return new ResponseEntity<OwnerDTOResponse>(objOwner, HttpStatus.OK);
        }catch(DataAccessException e){
            response.put("message", "Error when inserting into database");
            response.put("error", e.getMessage() + "" + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } 
}
