package com.prueba.aemet.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.aemet.modelDTO.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AemetService {

    @Autowired
    private RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(AemetService.class);

    @Value("${urlbase}")
    private String urlBase;

    @Value("${apiKey}")
    private String apiKey;

    @Value("${municipio}")
    private String municipio;

    @Value("${diasig}")
    private String diaSig;

    public List<MunicipioDTO> getMunicipios() throws JsonProcessingException {
        // Configurar la URL de la API
        String url = urlBase + municipio + apiKey;

        ResponseEntity<String> response = restTemplate.getForEntity(url,String.class);

        int statusCode = response.getStatusCodeValue();

        if (statusCode == 200) {
            // La solicitud fue exitosa
            String responseBody = String.valueOf(response.getBody());

            // Deserializar la respuesta a una lista de municipios utilizando Jackson
            ObjectMapper objectMapper = new ObjectMapper();
            List<MunicipioDTO> municipios = objectMapper.readValue(responseBody, new TypeReference<>() {});

            logger.info("Respuesta Municipios: " + statusCode);
            return municipios;
        } else {
            // La solicitud falló
            logger.info("La solicitud Municipios falló con el código de estado: " + statusCode);
        }

        return new ArrayList<>();

    }

    public PrediccionMunDiaSigDTO gePrediccionMunDiaSig(String idMunicipio, String unidadMedida){

        String numerosIdMun = idMunicipio.substring(2);
        String url = urlBase + diaSig + numerosIdMun +apiKey;

        ResponseEntity<String> response = restTemplate.getForEntity(url,String.class);

        int statusCode = response.getStatusCodeValue();

        if (statusCode == 200) {
            // La solicitud fue exitosa
            String responseData = response.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                DatosPrediccionDTO resp = objectMapper.readValue(responseData, new TypeReference<>() {});
                logger.info("Respuesta Dia Siguiente: " + statusCode);
                return obtenerDatosPredManiana(resp, unidadMedida);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } else {
            // La solicitud falló
            logger.info("La solicitud Dia Siguiente falló con el código de estado: " + statusCode);
        }

        return new PrediccionMunDiaSigDTO();

    }

    private PrediccionMunDiaSigDTO obtenerDatosPredManiana(DatosPrediccionDTO resp, String unidadMedida) {

        ResponseEntity<String> response = restTemplate.getForEntity(resp.getDatos(),String.class);

        int statusCode = response.getStatusCodeValue();

        if (statusCode == 200) {
            // La solicitud fue exitosa
            String responseData = response.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                List<RespuestaPrediccionDTO> respuestaPrediccionList = objectMapper.readValue(responseData, new TypeReference<>() {});

                if (respuestaPrediccionList != null && !respuestaPrediccionList.isEmpty()) {
                    RespuestaPrediccionDTO respuestaPrediccion = respuestaPrediccionList.get(0);
                    List<PrediccionDiaDTO> diasPredecidos = respuestaPrediccion.getPrediccion().getDia();
                    if (diasPredecidos != null && !diasPredecidos.isEmpty() && diasPredecidos.size() > 2) {
                        // Como obtiene las predicciones de los 7 días a partir de hoy, puedo coger el segundo valor de la lista que se corresponde con mañana
                        PrediccionDiaDTO maniana = diasPredecidos.get(1);

                        PrediccionMunDiaSigDTO prediccionMunDiaSig = new PrediccionMunDiaSigDTO();

                        prediccionMunDiaSig.setProbPrecipitacion(maniana.getProbPrecipitacion());
                        prediccionMunDiaSig.setUnidadTemperatura(unidadMedida);
                        double mediaTemperatura = (maniana.getTemperatura().getMaxima() + maniana.getTemperatura().getMinima()) / 2;
                        if (unidadMedida.equals("G_CEL")) {
                            logger.info("Respuesta Celsius: " + statusCode);
                            prediccionMunDiaSig.setMediaTemperatura(mediaTemperatura);
                        } else if (unidadMedida.equals("G_FAH")) {
                            // Se convierten de grados Celsius a Fahrenheit
                            logger.info("Respuesta Fahrenheit: " + statusCode);
                            prediccionMunDiaSig.setMediaTemperatura((mediaTemperatura * 9 / 5) + 32);
                        }
                        return prediccionMunDiaSig;
                    }
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } else {
            // La solicitud falló
            logger.info("La solicitud Medida de temperatura falló con el código de estado: " + statusCode);
        }

        return new PrediccionMunDiaSigDTO();
    }
}

