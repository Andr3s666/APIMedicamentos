package com.example.apirestmedicamentos.controller;

import com.example.apirestmedicamentos.model.Medicamento;
import com.example.apirestmedicamentos.repository.MedicamentoRepository;
import com.example.apirestmedicamentos.Event.MedicamentoCreadoEvent;
import com.example.apirestmedicamentos.Event.MedicamentoActualizadoEvent;
import com.example.apirestmedicamentos.Event.MedicamentoEliminadoEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/medicamentos")
public class MedicamentoController {

    private final MedicamentoRepository repository;
    private final ApplicationEventPublisher eventPublisher; //  PUBLISHER DE EVENTOS

    public MedicamentoController(MedicamentoRepository repository,
                                 ApplicationEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
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

    //  EVENT-DRIVEN: Publicar evento después de crear
    @PostMapping
    public Medicamento create(@RequestBody Medicamento medicamento) {
        Medicamento medicamentoGuardado = repository.save(medicamento);

        // 🎯 PUBLICAR EVENTO - "Alguien creó un medicamento"
        eventPublisher.publishEvent(new MedicamentoCreadoEvent(
                medicamentoGuardado.getId(),
                medicamentoGuardado.getNombre()
        ));

        return medicamentoGuardado;
    }

    //  EVENT-DRIVEN: Publicar evento antes de actualizar
    @PutMapping("/{id}")
    public ResponseEntity<Medicamento> update(@PathVariable Long id, @RequestBody Medicamento medicamento) {
        return repository.findById(id)
                .map(m -> {
                    //  PUBLICAR EVENTO - "Alguien actualizó un medicamento"
                    eventPublisher.publishEvent(new MedicamentoActualizadoEvent(
                            m.getId(), medicamento.getNombre(), medicamento.getPrecio()
                    ));

                    m.setNombre(medicamento.getNombre());
                    m.setDescripcion(medicamento.getDescripcion());
                    m.setPrecio(medicamento.getPrecio());
                    return ResponseEntity.ok(repository.save(m));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // EVENT-DRIVEN: Publicar evento antes de eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return repository.findById(id)
                .map(m -> {
                    //  PUBLICAR EVENTO - "Alguien eliminó un medicamento"
                    eventPublisher.publishEvent(new MedicamentoEliminadoEvent(
                            m.getId(), m.getNombre()
                    ));

                    repository.delete(m);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}