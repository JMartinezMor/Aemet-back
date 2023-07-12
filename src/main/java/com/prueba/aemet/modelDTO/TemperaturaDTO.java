package com.prueba.aemet.modelDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TemperaturaDTO {
    @JsonProperty("maxima")
    private int maxima;

    @JsonProperty("minima")
    private int minima;

    @JsonProperty("dato")
    private List<DatoDTO> datos;




}
