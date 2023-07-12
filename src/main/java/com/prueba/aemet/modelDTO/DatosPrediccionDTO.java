package com.prueba.aemet.modelDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DatosPrediccionDTO {
        @JsonProperty("descripcion")
        private String descripcion;

        @JsonProperty("estado")
        private int estado;

        @JsonProperty("datos")
        private String datos;

        @JsonProperty("metadatos")
        private String metadatos;

}
