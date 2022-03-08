package com.roerdev.pruebaapiionix.controllers;


import com.roerdev.pruebaapiionix.dto.ResponseDTO;
import com.roerdev.pruebaapiionix.dto.UserDto;
import com.roerdev.pruebaapiionix.exceptions.NotFoundException;
import com.roerdev.pruebaapiionix.services.UserService;
import com.roerdev.pruebaapiionix.utils.Constants;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "Servicio para obtener todos los usuarios registrados")
    @GetMapping
    @ResponseBody
    public ResponseEntity<?> getAllUser(Pageable paginador) {
        try {
            ResponseDTO response = new ResponseDTO(
                    HttpStatus.OK.getReasonPhrase(),
                    this.userService.getAllUser(paginador),
                    Constants.MESSAGE
            );

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("{}", e.getMessage());
            throw new RuntimeException("Error al obtener todos los usuarios.");
        }
    }

    @ApiOperation(value = "Servicio para obtener usuario por email")
    @GetMapping("/{email}")
    @ResponseBody
    public ResponseEntity<?> getUser (@PathVariable String email){

        try {
            ResponseDTO response = new ResponseDTO(
                    HttpStatus.OK.getReasonPhrase(),
                    this.userService.getUser(email),
                    Constants.MESSAGE
            );

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("{}", e.getMessage());
            throw new NotFoundException("Error al obtener usuario por email.");
        }
    }

    @ApiOperation(value = "Servicio para guardar un nuevo usuario")
    @PostMapping
    @ResponseBody
    public ResponseEntity<?> saveUser(@RequestBody UserDto user){

        try {
            ResponseDTO response = new ResponseDTO(
                    HttpStatus.CREATED.getReasonPhrase(),
                    this.userService.saveUser(user),
                    Constants.MESSAGE
            );

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("{}", e.getMessage());
            throw new RuntimeException("Error al guardar usuario.");
        }
    }

}
