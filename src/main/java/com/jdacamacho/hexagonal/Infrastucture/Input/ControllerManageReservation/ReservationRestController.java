package com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageReservation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdacamacho.hexagonal.Application.Input.ManageReservationCUIntPort;
import com.jdacamacho.hexagonal.Domain.Objects.Reservation;
import com.jdacamacho.hexagonal.Infrastucture.Input.ErrorCatcher;
import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageReservation.DTOResponse.ReservationDTOResponse;
import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageReservation.Mappers.MapperReservationInfrastructureDomainInt;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reservations")
@Validated
@RequiredArgsConstructor
public class ReservationRestController {
    private final ManageReservationCUIntPort reservationCU;
    private final MapperReservationInfrastructureDomainInt mapper;
    private final ErrorCatcher errorCatcher;

    @GetMapping("")
    @Transactional(readOnly = true)
    public ResponseEntity<List<ReservationDTOResponse>> index (){
        List<Reservation> reservations = this.reservationCU.listReservations();
        
        return new ResponseEntity<List<ReservationDTOResponse>>(
            this.mapper.mapModelToResponse(reservations),HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<List<ReservationDTOResponse>> indexUserId(@PathVariable long id){
        List<Reservation> reservations = this.reservationCU.listReservationByUserId(id);
        
        return new ResponseEntity<List<ReservationDTOResponse>>(
            this.mapper.mapModelToResponse(reservations),HttpStatus.OK);
    }

    @GetMapping("/users/name/")
    @Transactional(readOnly = true)
    public ResponseEntity<List<ReservationDTOResponse>> indexUserName(@RequestParam String name){
        List<Reservation> reservations = this.reservationCU.listReservationByUserName(name);
        
        return new ResponseEntity<List<ReservationDTOResponse>>(
            this.mapper.mapModelToResponse(reservations),HttpStatus.OK);
    }

    @PostMapping("/users/{idUser}/fields/{idField}/schedules/{idSchedule}")
    public ResponseEntity<?> makeReservation(@PathVariable long idUser,@PathVariable long idField ,
                                            @PathVariable long idSchedule, BindingResult result){

        Map<String, Object> response = new HashMap<>();
        response = this.errorCatcher.catchErrors(result);

        if(response.size() != 0){
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
        }

        try{
            ReservationDTOResponse objReservation = this.mapper.mapModelToResponse(this.reservationCU.makeReservation(idUser, idField, idSchedule));
            return new ResponseEntity<ReservationDTOResponse>(objReservation, HttpStatus.CREATED);
        }catch(DataAccessException e){
            response.put("message", "Error when inserting into database");
            response.put("error", e.getMessage() + "" + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> cancelReservation(@PathVariable long id){
        boolean deleted = this.reservationCU.cancelReservation(id);
        return ResponseEntity.ok(deleted);
    }

}
