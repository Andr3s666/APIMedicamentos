package com.example.apirestmedicamentos.Event;

public class MedicamentoActualizadoEvent {
    private Long medicamentoId;
    private String nombreMedicamento;
    private double nuevoPrecio;

    public MedicamentoActualizadoEvent(Long medicamentoId, String nombreMedicamento, double nuevoPrecio) {
        this.medicamentoId = medicamentoId;
        this.nombreMedicamento = nombreMedicamento;
        this.nuevoPrecio = nuevoPrecio;
    }

    // Getters
    public Long getMedicamentoId() { return medicamentoId; }
    public String getNombreMedicamento() { return nombreMedicamento; }
    public double getNuevoPrecio() { return nuevoPrecio; }
}