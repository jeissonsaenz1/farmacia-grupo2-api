
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cesde.farmaciagrupo2.dto.MedicamentoRequestDTO;
import com.cesde.farmaciagrupo2.dto.MedicamentoResponseDTO;
import com.cesde.farmaciagrupo2.service.MedicamentoService;

@RestController
@RequestMapping("/api/medicamentos")
public class MedicamentoController {

    private final MedicamentoService medicamentoService;

    public MedicamentoController(
            MedicamentoService medicamentoService) {
        this.medicamentoService = medicamentoService;
    }

    @PostMapping
    public ResponseEntity<MedicamentoResponseDTO> crear(
            @RequestBody MedicamentoRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(medicamentoService.guardar(dto));
    }

    @GetMapping
    public ResponseEntity<List<MedicamentoResponseDTO>> listar() {
        return ResponseEntity.ok(medicamentoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicamentoResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                medicamentoService.buscarPorId(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<MedicamentoResponseDTO>> buscarPorNombre(
            @RequestParam String nombre) {

        return ResponseEntity.ok(
                medicamentoService.buscarPorNombre(nombre));
    }

    @GetMapping("/laboratorio/{laboratorioId}")
    public ResponseEntity<List<MedicamentoResponseDTO>>
            buscarPorLaboratorio(@PathVariable Long laboratorioId) {

        return ResponseEntity.ok(
                medicamentoService.buscarPorLaboratorio(laboratorioId));
    }

    @GetMapping("/rango-precio")
    public ResponseEntity<List<MedicamentoResponseDTO>>
            buscarPorRangoPrecio(
                    @RequestParam Double precioMinimo,
                    @RequestParam Double precioMaximo) {

        return ResponseEntity.ok(
                medicamentoService.buscarPorRangoPrecio(
                        precioMinimo, precioMaximo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicamentoResponseDTO> actualizar(
            @PathVariable Long id,
            @RequestBody MedicamentoRequestDTO dto) {

        return ResponseEntity.ok(
                medicamentoService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        medicamentoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
