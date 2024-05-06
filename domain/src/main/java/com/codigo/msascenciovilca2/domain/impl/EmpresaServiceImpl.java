package com.codigo.msascenciovilca2.domain.impl;

import com.codigo.msascenciovilca2.domain.aggregates.dto.EmpresaDto;
import com.codigo.msascenciovilca2.domain.aggregates.request.EmpresaRequest;
import com.codigo.msascenciovilca2.domain.ports.in.EmpresaServiceIn;
import com.codigo.msascenciovilca2.domain.ports.out.EmpresaServiceOut;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmpresaServiceImpl implements EmpresaServiceIn {
    private final EmpresaServiceOut empresaServiceOut;
    @Override
    public EmpresaDto crearEmpresaIn(EmpresaRequest empresaRequest) {
        return empresaServiceOut.crearEmpresaOut(empresaRequest);
    }

    @Override
    public Optional<EmpresaDto> buscarXIdIn(Long id) {
        return empresaServiceOut.buscarXIdOut(id);
    }

    @Override
    public List<EmpresaDto> buscarTodosIn() {
        return empresaServiceOut.buscarTodosOut();
    }

    @Override
    public EmpresaDto actualizarIn(Long id, EmpresaRequest empresaRequest) {
        return empresaServiceOut.actualizarOut(id.intValue(), empresaRequest
        );
    }

    @Override
    public EmpresaDto eliminarIn(Long id) {
        return empresaServiceOut.eliminarOut(id.intValue());
    }
}
