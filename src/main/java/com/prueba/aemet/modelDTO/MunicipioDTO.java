package com.prueba.aemet.modelDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MunicipioDTO {
    @JsonProperty("latitud")
    private String latitud;

    @JsonProperty("id_old")
    private String idOld;

    @JsonProperty("url")
    private String url;

    @JsonProperty("latitud_dec")
    private String latitudDec;

    @JsonProperty("altitud")
    private String altitud;

    @JsonProperty("capital")
    private String capital;

    @JsonProperty("num_hab")
    private String numHab;

    @JsonProperty("zona_comarcal")
    private String zonaComarcal;

    @JsonProperty("destacada")
    private String destacada;

    @JsonProperty("nombre")
    private String nombre;

    @JsonProperty("longitud_dec")
    private String longitudDec;

    @JsonProperty("id")
    private String id;

    @JsonProperty("longitud")
    private String longitud;

}
