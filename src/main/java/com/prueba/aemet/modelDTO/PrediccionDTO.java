package com.prueba.aemet.modelDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PrediccionDTO {
    @JsonProperty("dia")
    private List<PrediccionDiaDTO> dia;

}
