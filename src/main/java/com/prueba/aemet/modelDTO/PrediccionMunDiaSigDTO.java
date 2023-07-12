package com.prueba.aemet.modelDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PrediccionMunDiaSigDTO {
    @JsonProperty("mediaTemperatura")
    private double mediaTemperatura;

    @JsonProperty("unidadTemperatura")
    private String unidadTemperatura;

    @JsonProperty("probPrecipitacion")
    private List<ProbPrecipitacionDTO> probPrecipitacion;

}
