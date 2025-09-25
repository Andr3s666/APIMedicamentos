package com.example.apirestmedicamentos.controller;

import com.example.apirestmedicamentos.model.Medicamento;
import com.example.apirestmedicamentos.repository.MedicamentoRepository;
import com.example.apirestmedicamentos.Event.MedicamentoCreadoEvent; // 🆕 Import del evento creación
import com.example.apirestmedicamentos.Event.MedicamentoActualizadoEvent; // 🆕 Import del evento actualización
import com.example.apirestmedicamentos.Event.MedicamentoEliminadoEvent; // 🆕 Import del evento eliminación
import org.springframework.context.ApplicationEventPublisher; // 🆕 Import del altavoz
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicamentos")
public class MedicamentoController {

    private final MedicamentoRepository repository;

    // 🆕 Para publicar eventos
    private final ApplicationEventPublisher eventPublisher;

    // 🆕 CONSTRUCTOR ACTUALIZADO - Agregar el eventPublisher aquí
    public MedicamentoController(MedicamentoRepository repository,
                                 ApplicationEventPublisher eventPublisher) { // 🆕 Agregar este parámetro
        this.repository = repository;
        this.eventPublisher = eventPublisher; // 🆕 Inyectamos el evento
    }

    // ✅ GET ALL
    @GetMapping
    public List<Medicamento> getAll() {
        return repository.findAll();
    }

    // ✅ GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Medicamento> getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 🆕 POST CREATE - CON EVENTO DE CREACIÓN
    @PostMapping
    public Medicamento create(@RequestBody Medicamento medicamento) {
        // 1. Guardar en BD
        Medicamento medicamentoGuardado = repository.save(medicamento);

        // 🆕 2. PUBLICAR EVENTO: "¡Se creo medicamento!"
        eventPublisher.publishEvent(
                new MedicamentoCreadoEvent(
                        medicamentoGuardado.getId(),
                        medicamentoGuardado.getNombre()
                )
        );
        System.out.println("📢 Evento de CREACIÓN publicado para medicamento: " + medicamentoGuardado.getNombre());

        // 3. Retornar respuesta normal
        return medicamentoGuardado;
    }

    // 🆕 PUT UPDATE - CON EVENTO DE ACTUALIZACIÓN
    @PutMapping("/{id}")
    public ResponseEntity<Medicamento> update(@PathVariable Long id, @RequestBody Medicamento medicamento) {
        return repository.findById(id)
                .map(m -> {
                    // 🆕 1. PUBLICAR EVENTO: "¡Actualizaron un medicamento!"
                    eventPublisher.publishEvent(
                            new MedicamentoActualizadoEvent(m.getId(), medicamento.getNombre(), medicamento.getPrecio())
                    );
                    System.out.println("📢 Evento de ACTUALIZACIÓN publicado para medicamento ID: " + m.getId());

                    // 2. Actualizar los datos
                    m.setNombre(medicamento.getNombre());
                    m.setDescripcion(medicamento.getDescripcion());
                    m.setPrecio(medicamento.getPrecio());

                    // 3. Guardar y retornar
                    return ResponseEntity.ok(repository.save(m));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 🆕 DELETE - CON EVENTO DE ELIMINACIÓN
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return repository.findById(id)
                .map(m -> {
                    // 🆕 1. PUBLICAR EVENTO: "¡Eliminaron un medicamento!"
                    eventPublisher.publishEvent(
                            new MedicamentoEliminadoEvent(m.getId(), m.getNombre())
                    );
                    System.out.println("📢 Evento de ELIMINACIÓN publicado para medicamento: " + m.getNombre());

                    // 2. Eliminar de la BD
                    repository.delete(m);

                    // 3. Retornar respuesta
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}


//1. ✅ El código guarda el medicamento en BD (lo normal)
//2. ✅ Publica un evento: "MedicamentoCreadoEvent"
//3. ✅ El MedicamentoCreadoListener escucha automáticamente
//4. ✅ El listener ejecuta SU código en segundo plano
//5. ✅ La API responde inmediatamente al cliente
