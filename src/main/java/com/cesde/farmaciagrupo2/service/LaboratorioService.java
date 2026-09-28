package com.cesde.farmaciagrupo2.service;

import com.cesde.farmaciagrupo2.model.entity.Laboratorio;
import com.cesde.farmaciagrupo2.repository.LaboratorioRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LaboratorioService {

    private final LaboratorioRepository laboratorioRepository;

    public LaboratorioService(LaboratorioRepository laboratorioRepository) {
        this.laboratorioRepository = laboratorioRepository;
    }

    public Laboratorio guardar(Laboratorio laboratorio) {
        return laboratorioRepository.save(laboratorio);
    }

    public List<Laboratorio> listar() {
        return laboratorioRepository.findAll();
    }

    public Laboratorio buscarPorId(Long id) {
        return laboratorioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Laboratorio no encontrado."
                ));
    }

    public Laboratorio actualizar(Long id, Laboratorio laboratorio) {

        Laboratorio laboratorioExistente = laboratorioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Laboratorio no encontrado."
                ));

        laboratorioExistente.setNombre(laboratorio.getNombre());
        laboratorioExistente.setDireccion(laboratorio.getDireccion());

        return laboratorioRepository.save(laboratorioExistente);
    }

    public void eliminar(Long id) {

        if (!laboratorioRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Laboratorio no encontrado."
            );
        }

        laboratorioRepository.deleteById(id);
    }
}