// 📁 event/MedicamentoCreadoEvent.java
package com.example.apirestmedicamentos.Event;

public class MedicamentoCreadoEvent {
    private Long medicamentoId;
    private String nombreMedicamento;

    // 🆕 CONSTRUCTOR - Para crear el evento con datos
    public MedicamentoCreadoEvent(Long medicamentoId, String nombreMedicamento) {
        this.medicamentoId = medicamentoId;
        this.nombreMedicamento = nombreMedicamento;
    }

    // GETTERS - Para que los listeners puedan leer la info`
    public Long getMedicamentoId() {
        return medicamentoId;
    }

    public String getNombreMedicamento() {
        return nombreMedicamento;
    }
}