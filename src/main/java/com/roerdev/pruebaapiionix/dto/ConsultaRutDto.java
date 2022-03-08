package com.roerdev.pruebaapiionix.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConsultaRutDto {

    private Integer responseCode;
    private String description;
    private ItemsDto result;
}
