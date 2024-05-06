package com.codigo.msascenciovilca2.domain.aggregates.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpresaDto {
    private String razonSocial;
    private String tipoDocumento;
    private String numeroDocumento;
    private Integer estado;
    private String condicion;
    private String direccion;
    private String distrito;
    private String provincia;
    private String departamento;
    private Boolean EsAgenteRetencion;
}
