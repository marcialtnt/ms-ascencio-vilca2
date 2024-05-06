package com.codigo.msascenciovilca2.domain.ports.in;

import com.codigo.msascenciovilca2.domain.aggregates.dto.EmpresaDto;
import com.codigo.msascenciovilca2.domain.aggregates.request.EmpresaRequest;

import java.util.List;
import java.util.Optional;

public interface EmpresaServiceIn {
    EmpresaDto crearEmpresaIn(EmpresaRequest empresaRequest);
    Optional<EmpresaDto> buscarXIdIn(Long id);
    List<EmpresaDto> buscarTodosIn();
    EmpresaDto actualizarIn(Long id, EmpresaRequest empresaRequest);
    EmpresaDto eliminarIn(Long id);
}
