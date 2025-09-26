package com.example.apirestmedicamentos.Event;

// 📌 Esta clase representa el EVENTO "Medicamento Eliminado".


public class MedicamentoEliminadoEvent {

    private Long medicamentoId;

    private String nombreMedicamento;

    public MedicamentoEliminadoEvent(Long medicamentoId, String nombreMedicamento) {
        this.medicamentoId = medicamentoId;
        this.nombreMedicamento = nombreMedicamento;
    }

    public Long getMedicamentoId() { return medicamentoId; }
    public String getNombreMedicamento() { return nombreMedicamento; }
}

