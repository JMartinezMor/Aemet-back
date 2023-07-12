package com.prueba.aemet;

import com.prueba.aemet.dto.MunicipioDTO;
import com.prueba.aemet.dto.PrediccionMunDiaSigDTO;
import com.prueba.aemet.service.AemetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class AemetServiceTest {
    @Autowired
    private AemetService aemetService;
    @Test
    void getMunicipios() {
        List<MunicipioDTO> municipioList = new ArrayList<MunicipioDTO>();
        municipioList = aemetService.getMunicipios();
        assertFalse(municipioList.isEmpty());
    }

    @Test
    void getPrediccionDiaSig() {

        PrediccionMunDiaSigDTO pred = aemetService.getPrediccionMunDiaSig("id18147","G_CEL");
        assertNotNull(pred);
    }
}
