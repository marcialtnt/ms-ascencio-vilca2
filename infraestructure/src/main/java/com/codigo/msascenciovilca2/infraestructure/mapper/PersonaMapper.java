package com.codigo.msascenciovilca2.infraestructure.mapper;

import com.codigo.msascenciovilca2.domain.aggregates.dto.PersonaDto;
import com.codigo.msascenciovilca2.infraestructure.entity.PersonaEntity;

public class PersonaMapper {
    public static PersonaDto fromEntity(PersonaEntity personaEntity) {
    PersonaDto dto = new PersonaDto();
    dto.setApellido(personaEntity.getApellido());
    dto.setNombre(personaEntity.getNombre());
    dto.setTipoDocumento(personaEntity.getTipoDocumento());
    dto.setNumeroDocumento(personaEntity.getNumeroDocumento());
    dto.setDireccion(personaEntity.getDireccion());
    dto.setEstado(personaEntity.getEstado());
    dto.setUsuaCrea(personaEntity.getUsuaCrea());
    dto.setDateCreate(personaEntity.getDateCreate());
    dto.setUsuaModif(personaEntity.getUsuaModif());
    dto.setDateModif(personaEntity.getDateModif());
    dto.setUsuaDelet(personaEntity.getUsuaDelet());
    dto.setDateDelet(personaEntity.getDateDelet());
    return dto;
    }
}
