package com.example.apirestmedicamentos.controller;

import com.example.apirestmedicamentos.model.Medicamento;
import com.example.apirestmedicamentos.repository.MedicamentoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicamentos")
public class MedicamentoController {

    private final MedicamentoRepository repository;

    public MedicamentoController(MedicamentoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Medicamento> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicamento> getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Medicamento create(@RequestBody Medicamento medicamento) {
        return repository.save(medicamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medicamento> update(@PathVariable Long id, @RequestBody Medicamento medicamento) {
        return repository.findById(id)
                .map(m -> {
                    m.setNombre(medicamento.getNombre());
                    m.setDescripcion(medicamento.getDescripcion());
                    m.setPrecio(medicamento.getPrecio());
                    return ResponseEntity.ok(repository.save(m));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return repository.findById(id)
                .map(m -> {
                    repository.delete(m);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
