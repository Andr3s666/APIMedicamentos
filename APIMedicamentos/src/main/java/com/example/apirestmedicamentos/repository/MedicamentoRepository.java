package com.example.apirestmedicamentos.repository;

import com.example.apirestmedicamentos.model.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
}
