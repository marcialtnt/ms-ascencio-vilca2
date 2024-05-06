package com.codigo.msascenciovilca2.application.controller;

import com.codigo.msascenciovilca2.domain.aggregates.dto.EmpresaDto;
import com.codigo.msascenciovilca2.domain.aggregates.request.EmpresaRequest;
import com.codigo.msascenciovilca2.domain.ports.in.EmpresaServiceIn;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ms-ascencio-vilca2/v1/empresa")
@AllArgsConstructor
public class EmpresaController {
    private final EmpresaServiceIn empresaServiceIn;

    @PostMapping("/crearEmpresa")
    public ResponseEntity<EmpresaDto> crearEmpresa(@RequestBody EmpresaRequest empresaRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(empresaServiceIn.crearEmpresaIn(empresaRequest));
    }

    @GetMapping("/buscarxId/{id}")
    public ResponseEntity<EmpresaDto> buscarxId(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(empresaServiceIn.buscarXIdIn(id).get());
    }

    @GetMapping("/buscartodos")
    public ResponseEntity<List<EmpresaDto>>buscartodos() {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(empresaServiceIn.buscarTodosIn());
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<EmpresaDto> actualizar(@PathVariable Long id, EmpresaRequest empresaRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(empresaServiceIn.actualizarIn(id, empresaRequest));
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<EmpresaDto> eliminar(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(empresaServiceIn.eliminarIn(id));
    }
}
