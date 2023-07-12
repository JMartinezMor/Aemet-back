package com.prueba.aemet.modelDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class SensTermicaDTO {
    @JsonProperty("maxima")
    private String maxima;

    @JsonProperty("minima")
    private String minima;

    @JsonProperty("dato")
    private List<DatoDTO> datos;
}
