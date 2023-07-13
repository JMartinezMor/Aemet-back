package com.prueba.aemet;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.prueba.aemet.modelDTO.MunicipioDTO;
import com.prueba.aemet.modelDTO.PrediccionMunDiaSigDTO;
import com.prueba.aemet.service.AemetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class AemetServiceTest {

    @Autowired
    private AemetService aemetService;
    @Test
    void getMunicipios() throws JsonProcessingException {
        List<MunicipioDTO> municipioList;
        municipioList = aemetService.getMunicipios();
        assertFalse(municipioList.isEmpty());
    }
//test
    @Test
    void getPrediccionDiaSig() {

        PrediccionMunDiaSigDTO pred = aemetService.gePrediccionMunDiaSig("id18147","G_CEL");
        assertNotNull(pred);
    }
}
