
package com.cesde.farmaciagrupo2.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cesde.farmaciagrupo2.dto.LaboratorioRequestDTO;
import com.cesde.farmaciagrupo2.dto.LaboratorioResponseDTO;
import com.cesde.farmaciagrupo2.service.LaboratorioService;

@RestController
@RequestMapping("/api/laboratorios")
public class LaboratorioController {

    private final LaboratorioService laboratorioService;

    public LaboratorioController(
            LaboratorioService laboratorioService) {
        this.laboratorioService = laboratorioService;
    }

    @PostMapping
    public ResponseEntity<LaboratorioResponseDTO> crear(
            @RequestBody LaboratorioRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(laboratorioService.guardar(dto));
    }

    @GetMapping
    public ResponseEntity<List<LaboratorioResponseDTO>> listar() {
        return ResponseEntity.ok(laboratorioService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LaboratorioResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                laboratorioService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LaboratorioResponseDTO> actualizar(
            @PathVariable Long id,
            @RequestBody LaboratorioRequestDTO dto) {

        return ResponseEntity.ok(
                laboratorioService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        laboratorioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
