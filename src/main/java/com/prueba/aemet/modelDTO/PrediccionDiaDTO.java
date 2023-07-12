package com.prueba.aemet.modelDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
@Data
public class PrediccionDiaDTO {
    @JsonProperty("probPrecipitacion")
    private List<ProbPrecipitacionDTO> probPrecipitacion;

    @JsonProperty("cotaNieveProv")
    private List<CotaNieveProvDTO> cotaNieveProv;

    @JsonProperty("estadoCielo")
    private List<EstadoCieloDTO> estadoCielo;

    @JsonProperty("viento")
    private List<VientoDTO> viento;

    @JsonProperty("rachaMax")
    private List<RachaMaxDTO> rachaMax;

    @JsonProperty("temperatura")
    private TemperaturaDTO temperatura;

    @JsonProperty("sensTermica")
    private SensTermicaDTO sensTermica;

    @JsonProperty("humedadRelativa")
    private HumedadRelativaDTO humedadRelativa;

    @JsonProperty("uvMax")
    private int uvMax;

    @JsonProperty("fecha")
    private String fecha;

}
