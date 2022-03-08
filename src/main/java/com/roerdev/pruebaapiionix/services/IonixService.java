package com.roerdev.pruebaapiionix.services;

import com.roerdev.pruebaapiionix.dto.ConsultaRutDto;

import java.io.IOException;

public interface IonixService {

    Integer consultaRut(String param) throws IOException;
}
