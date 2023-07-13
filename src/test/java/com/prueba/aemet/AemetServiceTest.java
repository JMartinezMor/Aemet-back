package com.prueba.aemet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.prueba.aemet.modelDTO.MunicipioDTO;
import com.prueba.aemet.modelDTO.PrediccionMunDiaSigDTO;
import com.prueba.aemet.modelDTO.ProbPrecipitacionDTO;
import com.prueba.aemet.service.AemetService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AemetServiceTest {

    @Test
    void getMunicipios() throws JsonProcessingException {
        List<MunicipioDTO> municipioList = new ArrayList<>();
        municipioList.add(new MunicipioDTO());
        AemetService aemetService=mock(AemetService.class);
        when(aemetService.getMunicipios()).thenReturn(municipioList);
        municipioList = aemetService.getMunicipios();
        assertFalse(municipioList.isEmpty());
    }

    @Test
    void getPrediccionDiaSig() {
        AemetService aemetService=mock(AemetService.class);
        PrediccionMunDiaSigDTO prediccion = new PrediccionMunDiaSigDTO();
        prediccion.setMediaTemperatura(30);
        prediccion.setUnidadTemperatura("G_CEL");
        prediccion.setProbPrecipitacion(new ArrayList<ProbPrecipitacionDTO>());
        when(aemetService.gePrediccionMunDiaSig("id18147","G_CEL")).thenReturn(prediccion);
        PrediccionMunDiaSigDTO pred = aemetService.gePrediccionMunDiaSig("id18147","G_CEL");
        assertNotNull(pred);
    }
}