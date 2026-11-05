
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

import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Tag(
        name = "Medicamentos",
        description = "Operaciones para gestionar medicamentos"
)
@RestController
@RequestMapping("/api/medicamentos")
public class MedicamentoController {

    private final MedicamentoService medicamentoService;

    public MedicamentoController(MedicamentoService medicamentoService) {
        this.medicamentoService = medicamentoService;
    }

    @Operation(
            summary = "Registrar medicamento",
            description = "Permite registrar un nuevo medicamento"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Medicamento creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    @PostMapping
    public ResponseEntity<MedicamentoResponseDTO> crear(
            @Valid @RequestBody MedicamentoRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(medicamentoService.guardar(dto));
    }

    @Operation(
            summary = "Listar medicamentos",
            description = "Obtiene el listado de medicamentos registrados"
    )
    @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
    @GetMapping
    public ResponseEntity<List<MedicamentoResponseDTO>> listar() {
        return ResponseEntity.ok(medicamentoService.listar());
    }

    @Operation(
            summary = "Consultar medicamento",
            description = "Busca un medicamento por su identificador"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Medicamento encontrado"),
            @ApiResponse(responseCode = "404", description = "Medicamento no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<MedicamentoResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(medicamentoService.buscarPorId(id));
    }

    @Operation(
            summary = "Buscar medicamentos por nombre",
            description = "Filtra medicamentos según su nombre"
    )
    @ApiResponse(responseCode = "200", description = "Búsqueda realizada correctamente")
    @GetMapping("/buscar")
    public ResponseEntity<List<MedicamentoResponseDTO>> buscarPorNombre(
            @RequestParam String nombre) {

        return ResponseEntity.ok(
                medicamentoService.buscarPorNombre(nombre));
    }

    @Operation(
            summary = "Buscar medicamentos por laboratorio",
            description = "Obtiene los medicamentos asociados a un laboratorio"
    )
    @ApiResponse(responseCode = "200", description = "Medicamentos encontrados")
    @GetMapping("/laboratorio/{laboratorioId}")
    public ResponseEntity<List<MedicamentoResponseDTO>> buscarPorLaboratorio(
            @PathVariable Long laboratorioId) {

        return ResponseEntity.ok(
                medicamentoService.buscarPorLaboratorio(laboratorioId));
    }

    @Operation(
            summary = "Buscar medicamentos por rango de precio",
            description = "Filtra medicamentos entre un precio mínimo y máximo"
    )
    @ApiResponse(responseCode = "200", description = "Búsqueda realizada correctamente")
    @GetMapping("/rango-precio")
    public ResponseEntity<List<MedicamentoResponseDTO>> buscarPorRangoPrecio(
            @RequestParam Double precioMinimo,
            @RequestParam Double precioMaximo) {

        return ResponseEntity.ok(
                medicamentoService.buscarPorRangoPrecio(
                        precioMinimo, precioMaximo));
    }

    @Operation(
            summary = "Actualizar medicamento",
            description = "Actualiza la información de un medicamento existente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Medicamento actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "404", description = "Medicamento no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<MedicamentoResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody MedicamentoRequestDTO dto) {

        return ResponseEntity.ok(
                medicamentoService.actualizar(id, dto));
    }

    @Operation(
            summary = "Eliminar medicamento",
            description = "Elimina un medicamento mediante su identificador"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Medicamento eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Medicamento no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        medicamentoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
