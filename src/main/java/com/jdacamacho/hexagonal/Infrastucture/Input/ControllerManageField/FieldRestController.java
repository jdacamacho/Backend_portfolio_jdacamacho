package com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageField;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdacamacho.hexagonal.Application.Input.ManageFieldCUIntPort;
import com.jdacamacho.hexagonal.Domain.Objects.Field;
import com.jdacamacho.hexagonal.Domain.Objects.Schedule;
import com.jdacamacho.hexagonal.Infrastucture.Input.ErrorCatcher;
import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageField.DTORequest.FieldDTORequest;
import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageField.DTOResponse.FieldDTOResponse;
import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageField.DTOResponse.ScheduleDTOResponse;
import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageField.mappers.MapperFieldInfrastructureDomainInt;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/fields")
@Validated
@RequiredArgsConstructor
public class FieldRestController {
    private final ManageFieldCUIntPort fieldCU;
    private final MapperFieldInfrastructureDomainInt mapper;
    private final ErrorCatcher errorCatcher;

    @GetMapping("/adm")
    @PreAuthorize("hasRole('Administrator')")
    @Transactional(readOnly = true)
    public ResponseEntity<List<FieldDTOResponse>> indexField(){
        List<Field> fields = this.fieldCU.listFields();
        
        return new ResponseEntity<List<FieldDTOResponse>>(
            this.mapper.mapModelToResponse(fields),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('Administrator', 'Owner')")
    @Transactional(readOnly = true)
    public ResponseEntity<FieldDTOResponse> getField(@PathVariable long id){
        Field field = this.fieldCU.findFieldById(id);
        
        return new ResponseEntity<FieldDTOResponse>(
            this.mapper.mapModelToResponse(field),HttpStatus.OK);
    }

    @GetMapping("/owners/id/{id}")
    @PreAuthorize("hasAnyRole('Administrator', 'Owner')")
    @Transactional(readOnly = true)
    public ResponseEntity<List<FieldDTOResponse>> indexFieldByIDOwner(@PathVariable long id){
        List<Field> fields = this.fieldCU.listFieldsByOwnerId(id);
        
        return new ResponseEntity<List<FieldDTOResponse>>(
            this.mapper.mapModelToResponse(fields),HttpStatus.OK);
    }

    @GetMapping("/name/")
    @PreAuthorize("hasAnyRole('Administrator', 'Client')")
    @Transactional(readOnly = true)
    public ResponseEntity<List<FieldDTOResponse>> indexFieldByPropertyName(@RequestParam String propertyName ){
        List<Field> fields = this.fieldCU.listFieldsByPropertyName(propertyName);
        
        return new ResponseEntity<List<FieldDTOResponse>>(
            this.mapper.mapModelToResponse(fields),HttpStatus.OK);
    }

    @GetMapping("/schedules/{id}")
    @PreAuthorize("hasAnyRole('Administrator', 'Owner','Client')")
    @Transactional(readOnly = true)
    public ResponseEntity<List<ScheduleDTOResponse>> indexSchedulesField(@PathVariable long id ){
        List<Schedule> schedules = this.fieldCU.getSchedulesById(id);
        
        return new ResponseEntity<List<ScheduleDTOResponse>>(
            this.mapper.mapModelsToResponse(schedules),HttpStatus.OK);
    }

    @GetMapping("/schedules/enable/{id}")
    @PreAuthorize("hasAnyRole('Administrator', 'Owner')")
    @Transactional(readOnly = true)
    public ResponseEntity<List<ScheduleDTOResponse>> enableFieldsSchedule(@PathVariable long id){
        List<Schedule> schedules = this.fieldCU.enableAllSchedules(id);
        
        return new ResponseEntity<List<ScheduleDTOResponse>>(
            this.mapper.mapModelsToResponse(schedules),HttpStatus.OK);
    }

    @PostMapping("/{idOwner}")
    @PreAuthorize("hasAnyRole('Administrator', 'Owner')")
    @Transactional
    public ResponseEntity<?> saveField(@PathVariable long idOwner ,@Valid @RequestBody FieldDTORequest request, BindingResult result){
        Field field = this.mapper.mapRequestToModel(request);
        
        Map<String, Object> response = new HashMap<>();
        response = this.errorCatcher.catchErrors(result);
        
        if(response.size() != 0){
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
        }

        try{
            FieldDTOResponse objField = this.mapper.mapModelToResponse(this.fieldCU.saveField(idOwner, field));
            return new ResponseEntity<FieldDTOResponse>(objField, HttpStatus.CREATED);
        }catch(DataAccessException e){
            response.put("message", "Error when inserting into database");
            response.put("error", e.getMessage() + "" + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('Administrator', 'Owner')")
    @Transactional
    public ResponseEntity<?> updateField(@PathVariable long id ,@Valid @RequestBody FieldDTORequest request, BindingResult result){
        Field field = this.mapper.mapRequestToModel(request);
        
        Map<String, Object> response = new HashMap<>();
        response = this.errorCatcher.catchErrors(result);
        
        if(response.size() != 0){
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
        }

        try{
            FieldDTOResponse objField = this.mapper.mapModelToResponse(this.fieldCU.updateField(id, field));
            return new ResponseEntity<FieldDTOResponse>(objField, HttpStatus.OK);
        }catch(DataAccessException e){
            response.put("message", "Error when updating into database");
            response.put("error", e.getMessage() + "" + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('Administrator', 'Owner')")
    @Transactional
    public ResponseEntity<Boolean> deleteField(@PathVariable long id) {
        boolean deleted = this.fieldCU.deleteField(id);
        return ResponseEntity.ok(deleted);
    }

}
