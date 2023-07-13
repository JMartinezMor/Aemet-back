package com.prueba.aemet.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.aemet.exception.HttpException;
import com.prueba.aemet.modelDTO.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

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
        String url = urlBase + municipio + apiKey;

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            int statusCode = response.getStatusCodeValue();

            if (statusCode == 200) {
                String responseBody = response.getBody();

                ObjectMapper objectMapper = new ObjectMapper();
                List<MunicipioDTO> municipios = objectMapper.readValue(responseBody, new TypeReference<>() {});

                logger.info("Respuesta Municipios: " + statusCode);
                return municipios;
            } else {
                logger.info("La solicitud Municipios falló con el código de estado: " + statusCode);
            }
        } catch (HttpClientErrorException e) {
            logger.error("Error al obtener los municipios: " + e.getMessage());
            throw new HttpException("Error al obtener los municipios", e);
        }

        return null;
    }

    public PrediccionMunDiaSigDTO gePrediccionMunDiaSig(String idMunicipio, String unidadMedida) {
        String numerosIdMun = idMunicipio.substring(2);
        String url = urlBase + diaSig + numerosIdMun + apiKey;

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            int statusCode = response.getStatusCodeValue();

            if (statusCode == 200) {

                String responseData = response.getBody();
                ObjectMapper objectMapper = new ObjectMapper();

                DatosPrediccionDTO resp = objectMapper.readValue(responseData, DatosPrediccionDTO.class);

                logger.info("Respuesta Dia Siguiente: " + statusCode);
                return obtenerDatosPredManiana(resp, unidadMedida);
            } else {
                logger.info("La solicitud Dia Siguiente falló con el código de estado: " + statusCode);
            }
        } catch (HttpClientErrorException e) {
            logger.error("Error al obtener la predicción del día siguiente: " + e.getMessage());
            throw new HttpException("Error al obtener la predicción del día siguiente", e);
        } catch (JsonProcessingException e) {
            logger.error("Error al procesar la respuesta de la API: " + e.getMessage());
            throw new HttpException("Error al procesar la respuesta de la API", e);
        }

        return new PrediccionMunDiaSigDTO();
    }

    private PrediccionMunDiaSigDTO obtenerDatosPredManiana(DatosPrediccionDTO resp, String unidadMedida) {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(resp.getDatos(), String.class);
            int statusCode = response.getStatusCodeValue();

            if (statusCode == 200) {
                String responseData = response.getBody();
                ObjectMapper objectMapper = new ObjectMapper();
                List<RespuestaPrediccionDTO> respuestaPrediccionList = objectMapper.readValue(responseData, new TypeReference<>() {});

                if (respuestaPrediccionList != null && !respuestaPrediccionList.isEmpty()) {
                    RespuestaPrediccionDTO respuestaPrediccion = respuestaPrediccionList.get(0);
                    List<PrediccionDiaDTO> diasPredecidos = respuestaPrediccion.getPrediccion().getDia();

                    if (diasPredecidos != null && diasPredecidos.size() > 1) {
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
            } else {
                logger.info("La solicitud Medida de temperatura falló con el código de estado: " + statusCode);
            }
        } catch (HttpClientErrorException e) {
            logger.error("Error al obtener los datos de la predicción del día siguiente: " + e.getMessage());
            throw new HttpException("Error al obtener los datos de la predicción del día siguiente", e);
        } catch (JsonProcessingException e) {
            logger.error("Error al procesar la respuesta de la API: " + e.getMessage());
            throw new HttpException("Error al procesar la respuesta de la API", e);
        }

        return new PrediccionMunDiaSigDTO();
    }
}

