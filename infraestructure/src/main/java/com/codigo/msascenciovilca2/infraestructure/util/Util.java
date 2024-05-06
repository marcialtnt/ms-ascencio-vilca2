package com.codigo.msascenciovilca2.infraestructure.util;

import com.codigo.msascenciovilca2.domain.aggregates.dto.EmpresaDto;
import com.codigo.msascenciovilca2.domain.aggregates.dto.PersonaDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Util {
    public static String convertirEmpresaAString(EmpresaDto empresaDto) throws JsonProcessingException {
        try {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(empresaDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String convertirPersonaAString(PersonaDto personaDto) throws JsonProcessingException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(personaDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T convertirDesdeString(String json, Class<T> tipoClase) throws JsonProcessingException {
        try {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, tipoClase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
