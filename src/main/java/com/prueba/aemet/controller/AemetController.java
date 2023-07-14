package com.prueba.aemet.controller;

import com.prueba.aemet.modelDTO.MunicipioDTO;
import com.prueba.aemet.modelDTO.PrediccionMunDiaSigDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.prueba.aemet.service.AemetService;

import java.io.IOException;
import java.util.List;

@RestController
public class AemetController {
    @Autowired
    private AemetService apiService;
//devuelve string
    @GetMapping("/api/municipios")
    public List<MunicipioDTO> getMunicipios() throws IOException {
        return apiService.getMunicipios();
    }

    @GetMapping("/api/prediccionDiaSiguiente")
    public PrediccionMunDiaSigDTO getPrediccionMunDiaSig(@RequestParam String idMunicipio, @RequestParam String unidadMedida) {
        return apiService.gePrediccionMunDiaSig(idMunicipio, unidadMedida);
    }


}
