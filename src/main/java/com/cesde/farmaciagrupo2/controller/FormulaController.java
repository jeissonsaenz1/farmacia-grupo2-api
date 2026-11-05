
package com.cesde.farmaciagrupo2.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cesde.farmaciagrupo2.dto.FormulaRequestDTO;
import com.cesde.farmaciagrupo2.dto.FormulaResponseDTO;
import com.cesde.farmaciagrupo2.service.FormulaService;

import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Tag(
        name = "Fórmulas",
        description = "Operaciones para gestionar fórmulas médicas"
)
@RestController
@RequestMapping("/api/formulas")
public class FormulaController {

    private final FormulaService formulaService;

    public FormulaController(FormulaService formulaService) {
        this.formulaService = formulaService;
    }

    @Operation(
            summary = "Registrar fórmula",
            description = "Permite registrar una nueva fórmula médica"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Fórmula creada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    @PostMapping
    public ResponseEntity<FormulaResponseDTO> crear(
            @Valid @RequestBody FormulaRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(formulaService.guardar(dto));
    }

    @Operation(
            summary = "Listar fórmulas",
            description = "Obtiene todas las fórmulas médicas registradas"
    )
    @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
    @GetMapping
    public ResponseEntity<List<FormulaResponseDTO>> listar() {
        return ResponseEntity.ok(formulaService.listar());
    }

    @Operation(
            summary = "Consultar fórmula",
            description = "Busca una fórmula médica mediante su identificador"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fórmula encontrada"),
            @ApiResponse(responseCode = "404", description = "Fórmula no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<FormulaResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(formulaService.buscarPorId(id));
    }

    @Operation(
            summary = "Actualizar fórmula",
            description = "Actualiza la información de una fórmula médica existente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fórmula actualizada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "404", description = "Fórmula no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<FormulaResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody FormulaRequestDTO dto) {

        return ResponseEntity.ok(
                formulaService.actualizar(id, dto)
        );
    }

    @Operation(
            summary = "Eliminar fórmula",
            description = "Elimina una fórmula médica mediante su identificador"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Fórmula eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "Fórmula no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        formulaService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}
