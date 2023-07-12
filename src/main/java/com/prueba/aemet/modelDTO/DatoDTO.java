package com.prueba.aemet.modelDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DatoDTO {
    @JsonProperty("value")
    private String value;

    @JsonProperty("hora")
    private String hora;
}
