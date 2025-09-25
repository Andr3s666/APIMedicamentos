package com.example.apirestmedicamentos.Event;

public class MedicamentoEliminadoEvent {
    private Long medicamentoId;
    private String nombreMedicamento;

    public MedicamentoEliminadoEvent(Long medicamentoId, String nombreMedicamento) {
        this.medicamentoId = medicamentoId;
        this.nombreMedicamento = nombreMedicamento;
    }

    // Getters
    public Long getMedicamentoId() { return medicamentoId; }
    public String getNombreMedicamento() { return nombreMedicamento; }
}