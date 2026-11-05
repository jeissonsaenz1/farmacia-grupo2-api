
package com.cesde.farmaciagrupo2.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cesde.farmaciagrupo2.dto.VentaRequestDTO;
import com.cesde.farmaciagrupo2.dto.VentaResponseDTO;
import com.cesde.farmaciagrupo2.model.enums.EstadoVenta;
import com.cesde.farmaciagrupo2.service.VentaService;

import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Tag(
        name = "Ventas",
        description = "Operaciones para gestionar las ventas de la farmacia"
)
@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @Operation(
            summary = "Registrar venta",
            description = "Permite registrar una nueva venta en la farmacia"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Venta creada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    @PostMapping
    public ResponseEntity<VentaResponseDTO> crear(
            @Valid @RequestBody VentaRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ventaService.guardar(dto));
    }

    @Operation(
            summary = "Listar ventas",
            description = "Obtiene todas las ventas registradas"
    )
    @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
    @GetMapping
    public ResponseEntity<List<VentaResponseDTO>> listar() {
        return ResponseEntity.ok(ventaService.listar());
    }

    @Operation(
            summary = "Consultar venta",
            description = "Busca una venta mediante su identificador"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venta encontrada"),
            @ApiResponse(responseCode = "404", description = "Venta no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<VentaResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(ventaService.buscarPorId(id));
    }

    @Operation(
            summary = "Buscar ventas por estado",
            description = "Obtiene las ventas que coinciden con el estado indicado"
    )
    @ApiResponse(responseCode = "200", description = "Búsqueda realizada correctamente")
    @GetMapping("/estado")
    public ResponseEntity<List<VentaResponseDTO>> buscarPorEstado(
            @RequestParam EstadoVenta estado) {

        return ResponseEntity.ok(
                ventaService.buscarPorEstado(estado)
        );
    }

    @Operation(
            summary = "Actualizar venta",
            description = "Actualiza la información de una venta existente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venta actualizada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "404", description = "Venta no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<VentaResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody VentaRequestDTO dto) {

        return ResponseEntity.ok(
                ventaService.actualizar(id, dto)
        );
    }

    @Operation(
            summary = "Eliminar venta",
            description = "Elimina una venta mediante su identificador"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Venta eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "Venta no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        ventaService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}
