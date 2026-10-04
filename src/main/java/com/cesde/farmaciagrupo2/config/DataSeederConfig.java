package com.cesde.farmaciagrupo2.config;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cesde.farmaciagrupo2.model.embeddable.Direccion;
import com.cesde.farmaciagrupo2.model.entity.DetalleVenta;
import com.cesde.farmaciagrupo2.model.entity.Formula;
import com.cesde.farmaciagrupo2.model.entity.Inventario;
import com.cesde.farmaciagrupo2.model.entity.Laboratorio;
import com.cesde.farmaciagrupo2.model.entity.Medicamento;
import com.cesde.farmaciagrupo2.model.entity.Venta;
import com.cesde.farmaciagrupo2.model.enums.EstadoVenta;
import com.cesde.farmaciagrupo2.repository.DetalleVentaRepository;
import com.cesde.farmaciagrupo2.repository.FormulaRepository;
import com.cesde.farmaciagrupo2.repository.InventarioRepository;
import com.cesde.farmaciagrupo2.repository.LaboratorioRepository;
import com.cesde.farmaciagrupo2.repository.MedicamentoRepository;
import com.cesde.farmaciagrupo2.repository.VentaRepository;

@Configuration
public class DataSeederConfig {

    @Bean
    CommandLineRunner initDatabase(
            LaboratorioRepository laboratorioRepository,
            MedicamentoRepository medicamentoRepository,
            InventarioRepository inventarioRepository,
            FormulaRepository formulaRepository,
            VentaRepository ventaRepository,
            DetalleVentaRepository detalleVentaRepository) {

        return args -> {

            // Evita insertar los datos nuevamente si ya existen.
            if (laboratorioRepository.count() > 0) {
                System.out.println("Los datos iniciales ya existen.");
                return;
            }

            // =========================================================
            // 1. LABORATORIOS
            // =========================================================

            Laboratorio laboratorio1 = Laboratorio.builder()
                    .nombre("Laboratorios Genfar")
                    .direccion(Direccion.builder()
                            .calle("Calle 10 # 20-30")
                            .ciudad("Medellín")
                            .pais("Colombia")
                            .build())
                    .estadoActivo(true)
                    .build();

            Laboratorio laboratorio2 = Laboratorio.builder()
                    .nombre("Laboratorios MK")
                    .direccion(Direccion.builder()
                            .calle("Carrera 50 # 40-25")
                            .ciudad("Bogotá")
                            .pais("Colombia")
                            .build())
                    .estadoActivo(true)
                    .build();

            laboratorioRepository.saveAll(
                    List.of(laboratorio1, laboratorio2)
            );

            // =========================================================
            // 2. MEDICAMENTOS
            // =========================================================

            Medicamento medicamento1 = Medicamento.builder()
                    .nombre("Acetaminofén 500 mg")
                    .precio(5000.0)
                    .laboratorio(laboratorio1)
                    .estadoActivo(true)
                    .build();

            Medicamento medicamento2 = Medicamento.builder()
                    .nombre("Ibuprofeno 400 mg")
                    .precio(7500.0)
                    .laboratorio(laboratorio1)
                    .estadoActivo(true)
                    .build();

            Medicamento medicamento3 = Medicamento.builder()
                    .nombre("Loratadina 10 mg")
                    .precio(9000.0)
                    .laboratorio(laboratorio2)
                    .estadoActivo(true)
                    .build();

            medicamentoRepository.saveAll(
                    List.of(
                            medicamento1,
                            medicamento2,
                            medicamento3
                    )
            );

            // =========================================================
            // 3. INVENTARIOS
            // =========================================================

            Inventario inventario1 = Inventario.builder()
                    .stock(100)
                    .lote("LOT-ACET-001")
                    .fechaVencimiento(LocalDate.now().plusMonths(12))
                    .medicamento(medicamento1)
                    .estadoActivo(true)
                    .build();

            Inventario inventario2 = Inventario.builder()
                    .stock(80)
                    .lote("LOT-IBU-001")
                    .fechaVencimiento(LocalDate.now().plusMonths(15))
                    .medicamento(medicamento2)
                    .estadoActivo(true)
                    .build();

            Inventario inventario3 = Inventario.builder()
                    .stock(60)
                    .lote("LOT-LORA-001")
                    .fechaVencimiento(LocalDate.now().plusMonths(18))
                    .medicamento(medicamento3)
                    .estadoActivo(true)
                    .build();

            inventarioRepository.saveAll(
                    List.of(
                            inventario1,
                            inventario2,
                            inventario3
                    )
            );

            // =========================================================
            // 4. FÓRMULAS
            // =========================================================

            Formula formula1 = Formula.builder()
                    .nombre("Analgésicos básicos")
                    .medicamentos(new HashSet<>(
                            Set.of(
                                    medicamento1,
                                    medicamento2
                            )
                    ))
                    .estadoActivo(true)
                    .build();

            Formula formula2 = Formula.builder()
                    .nombre("Antialérgicos")
                    .medicamentos(new HashSet<>(
                            Set.of(medicamento3)
                    ))
                    .estadoActivo(true)
                    .build();

            formulaRepository.saveAll(
                    List.of(
                            formula1,
                            formula2
                    )
            );

            // =========================================================
            // 5. VENTA
            // =========================================================

            Venta venta = Venta.builder()
                    .total(12500.0)
                    .estado(EstadoVenta.PAGADA)
                    .estadoActivo(true)
                    .build();

            ventaRepository.save(venta);

            // =========================================================
            // 6. DETALLES DE VENTA
            // =========================================================

            DetalleVenta detalle1 = DetalleVenta.builder()
                    .cantidad(1)
                    .subtotal(5000.0)
                    .venta(venta)
                    .medicamento(medicamento1)
                    .estadoActivo(true)
                    .build();

            DetalleVenta detalle2 = DetalleVenta.builder()
                    .cantidad(1)
                    .subtotal(7500.0)
                    .venta(venta)
                    .medicamento(medicamento2)
                    .estadoActivo(true)
                    .build();

            detalleVentaRepository.saveAll(
                    List.of(
                            detalle1,
                            detalle2
                    )
            );

            System.out.println("======================================");
            System.out.println("Datos iniciales cargados correctamente.");
            System.out.println("======================================");
        };
    }
}