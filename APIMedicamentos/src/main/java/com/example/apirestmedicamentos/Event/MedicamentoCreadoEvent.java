// 📁 event/MedicamentoCreadoEvent.java
package com.example.apirestmedicamentos.Event;

// 📌 Esta clase representa el EVENTO "Medicamento Creado".


public class MedicamentoCreadoEvent {

    private Long medicamentoId;

    private String nombreMedicamento;

    public MedicamentoCreadoEvent(Long medicamentoId, String nombreMedicamento) {
        this.medicamentoId = medicamentoId;
        this.nombreMedicamento = nombreMedicamento;
    }

    public Long getMedicamentoId() {
        return medicamentoId;
    }

    public String getNombreMedicamento() {
        return nombreMedicamento;
    }
}

