package com.cesde.farmaciagrupo2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cesde.farmaciagrupo2.exception.RecursoNoEncontradoException;
import com.cesde.farmaciagrupo2.exception.ReglaDeNegocioException;
import com.cesde.farmaciagrupo2.model.entity.Medicamento;
import com.cesde.farmaciagrupo2.repository.MedicamentoRepository;

@Service
public class MedicamentoService {

    private final MedicamentoRepository medicamentoRepository;

    public MedicamentoService(MedicamentoRepository medicamentoRepository) {
        this.medicamentoRepository = medicamentoRepository;
    }

    public Medicamento guardar(Medicamento medicamento) {

        // Regla de negocio 1:
        // El precio debe ser mayor que cero.
        validarPrecio(medicamento);

        return medicamentoRepository.save(medicamento);
    }

    public List<Medicamento> listar() {
        return medicamentoRepository.findAll();
    }

    public Medicamento buscarPorId(Long id) {
    return medicamentoRepository.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException(
                    "Medicamento no encontrado."
            ));
}

    public List<Medicamento> buscarPorNombre(String nombre) {
        return medicamentoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Medicamento> buscarPorLaboratorio(Long laboratorioId) {
        return medicamentoRepository.findByLaboratorioId(laboratorioId);
    }

    public List<Medicamento> buscarPorRangoPrecio(
            Double precioMinimo,
            Double precioMaximo) {

        return medicamentoRepository.findByPrecioBetween(
                precioMinimo,
                precioMaximo);
    }

    public Medicamento actualizar(Long id, Medicamento medicamento) {

        Medicamento medicamentoExistente = medicamentoRepository.findById(id)
        .orElseThrow(() -> new RecursoNoEncontradoException(
                "Medicamento no encontrado."
        ));
        
        // Se mantiene la regla de negocio del precio
        validarPrecio(medicamento);

        medicamentoExistente.setNombre(medicamento.getNombre());
        medicamentoExistente.setPrecio(medicamento.getPrecio());
        medicamentoExistente.setLaboratorio(medicamento.getLaboratorio());

        return medicamentoRepository.save(medicamentoExistente);
    }

    public void eliminar(Long id) {

        if (!medicamentoRepository.existsById(id)) {
            throw new RecursoNoEncontradoException(
        "Medicamento no encontrado."
            );
        }

        medicamentoRepository.deleteById(id);
    }

    private void validarPrecio(Medicamento medicamento) {

        if (medicamento.getPrecio() == null || medicamento.getPrecio() <= 0) {
            throw new ReglaDeNegocioException(
                    "El precio del medicamento debe ser mayor que cero."
            );
        }
    }
}