package com.cesde.farmaciagrupo2.service;

import com.cesde.farmaciagrupo2.model.entity.Venta;
import com.cesde.farmaciagrupo2.model.enums.EstadoVenta;
import com.cesde.farmaciagrupo2.repository.VentaRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;

    public VentaService(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    public Venta guardar(Venta venta) {
        return ventaRepository.save(venta);
    }

    public List<Venta> listar() {
        return ventaRepository.findAll();
    }

    public Venta buscarPorId(Long id) {
        return ventaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Venta no encontrada."
                ));
    }

    public Venta actualizar(Long id, Venta venta) {

        Venta ventaExistente = ventaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Venta no encontrada."
                ));

        ventaExistente.setTotal(venta.getTotal());
        ventaExistente.setEstado(venta.getEstado());

        return ventaRepository.save(ventaExistente);
    }

    public void eliminar(Long id) {

        if (!ventaRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Venta no encontrada."
            );
        }

        ventaRepository.deleteById(id);
    }

    public List<Venta> buscarPorEstado(EstadoVenta estado) {
        return ventaRepository.findByEstado(estado);
    }
}