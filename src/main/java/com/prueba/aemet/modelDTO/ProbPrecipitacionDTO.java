package com.prueba.aemet.modelDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProbPrecipitacionDTO {
    @JsonProperty("value")
    private int value;

    @JsonProperty("periodo")
    private String periodo;

}
