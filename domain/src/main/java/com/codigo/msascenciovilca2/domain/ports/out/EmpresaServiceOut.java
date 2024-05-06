package com.codigo.msascenciovilca2.domain.ports.out;

import com.codigo.msascenciovilca2.domain.aggregates.dto.EmpresaDto;
import com.codigo.msascenciovilca2.domain.aggregates.request.EmpresaRequest;

import java.util.List;
import java.util.Optional;

public interface EmpresaServiceOut {
    EmpresaDto crearEmpresaOut(EmpresaRequest empresaRequest);
    Optional<EmpresaDto> buscarXIdOut(Long id);
    List<EmpresaDto> buscarTodosOut();
    EmpresaDto actualizarOut(Integer id, EmpresaRequest empresaRequest);
    EmpresaDto eliminarOut(Integer id);
}
