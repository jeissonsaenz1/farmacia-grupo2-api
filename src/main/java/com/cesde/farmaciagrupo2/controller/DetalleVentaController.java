
package com.cesde.farmaciagrupo2.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cesde.farmaciagrupo2.dto.DetalleVentaRequestDTO;
import com.cesde.farmaciagrupo2.dto.DetalleVentaResponseDTO;
import com.cesde.farmaciagrupo2.service.DetalleVentaService;

import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Tag(
        name = "Detalles de venta",
        description = "Operaciones para gestionar los detalles de las ventas"
)
@RestController
@RequestMapping("/api/detalles-venta")
public class DetalleVentaController {

    private final DetalleVentaService detalleVentaService;

    public DetalleVentaController(
            DetalleVentaService detalleVentaService) {
        this.detalleVentaService = detalleVentaService;
    }

    @Operation(
            summary = "Registrar detalle de venta",
            description = "Permite registrar un nuevo detalle asociado a una venta"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Detalle de venta creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    @PostMapping
    public ResponseEntity<DetalleVentaResponseDTO> crear(
            @Valid @RequestBody DetalleVentaRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(detalleVentaService.guardar(dto));
    }

    @Operation(
            summary = "Listar detalles de venta",
            description = "Obtiene todos los detalles de venta registrados"
    )
    @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
    @GetMapping
    public ResponseEntity<List<DetalleVentaResponseDTO>> listar() {
        return ResponseEntity.ok(detalleVentaService.listar());
    }

    @Operation(
            summary = "Consultar detalle de venta",
            description = "Busca un detalle de venta mediante su identificador"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalle de venta encontrado"),
            @ApiResponse(responseCode = "404", description = "Detalle de venta no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DetalleVentaResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                detalleVentaService.buscarPorId(id)
        );
    }

    @Operation(
            summary = "Actualizar detalle de venta",
            description = "Actualiza la información de un detalle de venta existente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalle de venta actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "404", description = "Detalle de venta no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<DetalleVentaResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody DetalleVentaRequestDTO dto) {

        return ResponseEntity.ok(
                detalleVentaService.actualizar(id, dto)
        );
    }

    @Operation(
            summary = "Eliminar detalle de venta",
            description = "Elimina un detalle de venta mediante su identificador"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Detalle de venta eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Detalle de venta no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        detalleVentaService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}
