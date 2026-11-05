
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

import com.cesde.farmaciagrupo2.dto.InventarioRequestDTO;
import com.cesde.farmaciagrupo2.dto.InventarioResponseDTO;
import com.cesde.farmaciagrupo2.service.InventarioService;

import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Tag(
        name = "Inventarios",
        description = "Operaciones para gestionar el inventario de medicamentos"
)
@RestController
@RequestMapping("/api/inventarios")
public class InventarioController {

    private final InventarioService inventarioService;

    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    @Operation(
            summary = "Registrar inventario",
            description = "Permite registrar un nuevo inventario de medicamentos"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Inventario creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    @PostMapping
    public ResponseEntity<InventarioResponseDTO> crear(
            @Valid @RequestBody InventarioRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(inventarioService.guardar(dto));
    }

    @Operation(
            summary = "Listar inventarios",
            description = "Obtiene todos los registros de inventario"
    )
    @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
    @GetMapping
    public ResponseEntity<List<InventarioResponseDTO>> listar() {
        return ResponseEntity.ok(inventarioService.listar());
    }

    @Operation(
            summary = "Consultar inventario",
            description = "Busca un registro de inventario mediante su identificador"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inventario encontrado"),
            @ApiResponse(responseCode = "404", description = "Inventario no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<InventarioResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return inventarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Consultar inventario por medicamento",
            description = "Busca el inventario asociado a un medicamento específico"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inventario encontrado"),
            @ApiResponse(responseCode = "404", description = "Inventario no encontrado")
    })
    @GetMapping("/medicamento/{medicamentoId}")
    public ResponseEntity<InventarioResponseDTO> buscarPorMedicamento(
            @PathVariable Long medicamentoId) {

        return inventarioService.buscarPorMedicamento(medicamentoId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Actualizar inventario",
            description = "Actualiza la información de un registro de inventario"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inventario actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "404", description = "Inventario no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<InventarioResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody InventarioRequestDTO dto) {

        return ResponseEntity.ok(
                inventarioService.actualizar(id, dto)
        );
    }

    @Operation(
            summary = "Eliminar inventario",
            description = "Elimina un registro de inventario mediante su identificador"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Inventario eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Inventario no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        inventarioService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}
