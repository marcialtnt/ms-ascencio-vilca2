package com.codigo.msascenciovilca2.domain.impl;

import com.codigo.msascenciovilca2.domain.aggregates.dto.PersonaDto;
import com.codigo.msascenciovilca2.domain.aggregates.request.PersonaRequest;
import com.codigo.msascenciovilca2.domain.ports.in.PersonaServiceIn;
import com.codigo.msascenciovilca2.domain.ports.out.PersonaServiceOut;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonaServiceImpl implements PersonaServiceIn {
    private final PersonaServiceOut personaServiceOut;
    @Override
    public PersonaDto crearPersonaIn(PersonaRequest personaRequest) {
        return personaServiceOut.crearPersonaOut(personaRequest);
    }

    @Override
    public Optional<PersonaDto> buscarxIdIn(Long id) {
        return personaServiceOut.buscarxIdOut(id);
    }

    @Override
    public List<PersonaDto> buscartodosIn() {
        return personaServiceOut.buscartodosOut();
    }

    @Override
    public PersonaDto actualizarIn(Long id, PersonaRequest personaRequest) {
        return personaServiceOut.actualizarOut(id, personaRequest);
    }

    @Override
    public PersonaDto eliminarIn(Long id) {
        return personaServiceOut.eliminarOut(id);
    }
}
