package com.prueba.aemet.modelDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CotaNieveProvDTO {
    @JsonProperty("value")
    private String value;

    @JsonProperty("periodo")
    private String periodo;

}
