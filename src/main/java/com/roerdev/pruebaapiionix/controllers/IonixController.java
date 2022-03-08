package com.roerdev.pruebaapiionix.controllers;

import com.roerdev.pruebaapiionix.dto.ConsultaInputDto;
import com.roerdev.pruebaapiionix.dto.ResponseDTO;
import com.roerdev.pruebaapiionix.dto.ResultDto;
import com.roerdev.pruebaapiionix.dto.UserDto;
import com.roerdev.pruebaapiionix.services.IonixService;
import com.roerdev.pruebaapiionix.utils.Constants;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.Instant;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/ionix")
public class IonixController {

    private final IonixService ionixService;

    @ApiOperation(value = "Servicio para consulta externa")
    @PostMapping
    @ResponseBody
    public ResponseEntity<?> saveUser(@RequestBody ConsultaInputDto consulta){

        ResponseDTO response = new ResponseDTO();
        Instant start = Instant.now();
        try {
            var numReg = ionixService.consultaRut(consulta.getParam());
            ResultDto resultDto = new ResultDto(numReg);
            Instant end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            response = new ResponseDTO(
                    HttpStatus.OK.value(),
                    HttpStatus.OK.getReasonPhrase(),
                    timeElapsed.toMillis(),
                    resultDto
            );

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("{}", e.getMessage());
            throw new RuntimeException("Error al guardar usuario.");
        }
    }
}
