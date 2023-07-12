package com.prueba.aemet.modelDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class VientoDTO {
    @JsonProperty("velocidad")
    private String velocidad;

    @JsonProperty("direccion")
    private String direccion;

    @JsonProperty("periodo")
    private String periodo;

}
