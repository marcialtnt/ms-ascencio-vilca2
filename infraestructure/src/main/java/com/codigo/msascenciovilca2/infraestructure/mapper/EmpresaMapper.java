package com.codigo.msascenciovilca2.infraestructure.mapper;

import com.codigo.msascenciovilca2.domain.aggregates.dto.EmpresaDto;
import com.codigo.msascenciovilca2.infraestructure.entity.EmpresaEntity;

public class EmpresaMapper {
    public static EmpresaDto fromEntity(EmpresaEntity entity) {
        EmpresaDto dto = new EmpresaDto();
        dto.setTipoDocumento(entity.getTipoDocumento());
        dto.setNumeroDocumento(entity.getNumeroDocumento());
        dto.setEstado(entity.getEstado());
        dto.setCondicion(entity.getCondicion());
        dto.setDireccion(entity.getDireccion());
        dto.setDistrito(entity.getDistrito());
        dto.setProvincia(entity.getProvincia());
        dto.setDepartamento(entity.getDepartamento());
        dto.setEsAgenteRetencion(entity.getEsAgenteRetencion());
        return dto;
    }
}
