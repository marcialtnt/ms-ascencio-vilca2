package com.codigo.msascenciovilca2.application.controller;

import com.codigo.msascenciovilca2.domain.aggregates.dto.PersonaDto;
import com.codigo.msascenciovilca2.domain.aggregates.request.PersonaRequest;
import com.codigo.msascenciovilca2.domain.ports.in.PersonaServiceIn;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/ms-ascencio-vilca2/v1/persona")
public class PersonaController {
    private final PersonaServiceIn personaServiceIn;

    @PostMapping("/crearPersona")
    public ResponseEntity<PersonaDto> crearPersona(@RequestBody PersonaRequest personaRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personaServiceIn.crearPersonaIn(personaRequest));
    }

    @GetMapping("/buscarxId/{id}")
    public ResponseEntity<PersonaDto> buscarxId(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personaServiceIn.buscarxIdIn(id).get());
    }

    @GetMapping("/buscartodos")
    public ResponseEntity<List<PersonaDto>> buscartodos() {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personaServiceIn.buscartodosIn());
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<PersonaDto> actualizar(@PathVariable Long id, @RequestBody PersonaRequest personaRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personaServiceIn.actualizarIn(id, personaRequest));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<PersonaDto> eliminar(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personaServiceIn.eliminarIn(id));
    }
}