package com.prueba.aemet.modelDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrigenDTO {
        @JsonProperty("productor")
        private String productor;

        @JsonProperty("web")
        private String web;

        @JsonProperty("enlace")
        private String enlace;

        @JsonProperty("language")
        private String language;

        @JsonProperty("copyright")
        private String copyright;

        @JsonProperty("notaLegal")
        private String notaLegal;

}
