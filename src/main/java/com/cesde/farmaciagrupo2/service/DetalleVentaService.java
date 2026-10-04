package com.cesde.farmaciagrupo2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cesde.farmaciagrupo2.exception.RecursoNoEncontradoException;
import com.cesde.farmaciagrupo2.model.entity.DetalleVenta;
import com.cesde.farmaciagrupo2.repository.DetalleVentaRepository;

@Service
public class DetalleVentaService {

    private final DetalleVentaRepository detalleVentaRepository;

    public DetalleVentaService(DetalleVentaRepository detalleVentaRepository) {
        this.detalleVentaRepository = detalleVentaRepository;
    }

    public DetalleVenta guardar(DetalleVenta detalleVenta) {
        return detalleVentaRepository.save(detalleVenta);
    }

    public List<DetalleVenta> listar() {
        return detalleVentaRepository.findAll();
    }

    public DetalleVenta buscarPorId(Long id) {
        return detalleVentaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                "Detalle de venta no encontrado."
        ));
    }

    public DetalleVenta actualizar(Long id, DetalleVenta detalleVenta) {

        DetalleVenta detalleExistente = detalleVentaRepository.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException(
                    "Detalle de venta no encontrado."
            ));

        detalleExistente.setCantidad(detalleVenta.getCantidad());
        detalleExistente.setSubtotal(detalleVenta.getSubtotal());
        detalleExistente.setVenta(detalleVenta.getVenta());
        detalleExistente.setMedicamento(detalleVenta.getMedicamento());

        return detalleVentaRepository.save(detalleExistente);
    }

    public void eliminar(Long id) {

        if (!detalleVentaRepository.existsById(id)) {
            throw new RecursoNoEncontradoException(
                    "Detalle de venta no encontrado."
            );
        }

        detalleVentaRepository.deleteById(id);
    }
}