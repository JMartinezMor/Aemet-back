package com.prueba.aemet.modelDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RespuestaPrediccionDTO {
    @JsonProperty("origen")
    private OrigenDTO origen;

    @JsonProperty("elaborado")
    private String elaborado;

    @JsonProperty("nombre")
    private String nombre;

    @JsonProperty("provincia")
    private String provincia;

    @JsonProperty("prediccion")
    private PrediccionDTO prediccion;

    @JsonProperty("id")
    private String id;

    @JsonProperty("version")
    private String version;
}
