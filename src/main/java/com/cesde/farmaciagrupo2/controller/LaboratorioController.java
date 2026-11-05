
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

import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Tag(
        name = "Laboratorios",
        description = "Operaciones para gestionar laboratorios farmacéuticos"
)
@RestController
@RequestMapping("/api/laboratorios")
public class LaboratorioController {

    private final LaboratorioService laboratorioService;

    public LaboratorioController(
            LaboratorioService laboratorioService) {
        this.laboratorioService = laboratorioService;
    }

    @Operation(
            summary = "Registrar laboratorio",
            description = "Permite registrar un nuevo laboratorio farmacéutico"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Laboratorio creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    @PostMapping
    public ResponseEntity<LaboratorioResponseDTO> crear(
            @Valid @RequestBody LaboratorioRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(laboratorioService.guardar(dto));
    }

    @Operation(
            summary = "Listar laboratorios",
            description = "Obtiene todos los laboratorios registrados"
    )
    @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
    @GetMapping
    public ResponseEntity<List<LaboratorioResponseDTO>> listar() {
        return ResponseEntity.ok(laboratorioService.listar());
    }

    @Operation(
            summary = "Consultar laboratorio",
            description = "Busca un laboratorio mediante su identificador"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Laboratorio encontrado"),
            @ApiResponse(responseCode = "404", description = "Laboratorio no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<LaboratorioResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                laboratorioService.buscarPorId(id));
    }

    @Operation(
            summary = "Actualizar laboratorio",
            description = "Actualiza la información de un laboratorio existente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Laboratorio actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "404", description = "Laboratorio no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<LaboratorioResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody LaboratorioRequestDTO dto) {

        return ResponseEntity.ok(
                laboratorioService.actualizar(id, dto));
    }

    @Operation(
            summary = "Eliminar laboratorio",
            description = "Elimina un laboratorio mediante su identificador"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Laboratorio eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Laboratorio no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        laboratorioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
