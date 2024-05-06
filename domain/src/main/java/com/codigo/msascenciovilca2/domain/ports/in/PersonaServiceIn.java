package com.codigo.msascenciovilca2.domain.ports.in;

import com.codigo.msascenciovilca2.domain.aggregates.dto.PersonaDto;
import com.codigo.msascenciovilca2.domain.aggregates.request.PersonaRequest;

import java.util.List;
import java.util.Optional;

public interface PersonaServiceIn {
    PersonaDto crearPersonaIn(PersonaRequest personaRequest);
    Optional<PersonaDto> buscarxIdIn(Long id);
    List<PersonaDto> buscartodosIn();
    PersonaDto actualizarIn(Long id, PersonaRequest personaRequest);
    PersonaDto eliminarIn(Long id);
}
