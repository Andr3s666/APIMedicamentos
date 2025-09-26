package com.example.apirestmedicamentos.Event;

// 📌 Esta clase representa un EVENTO específico: "Un medicamento fue actualizado".


public class MedicamentoActualizadoEvent {

    private Long medicamentoId;

    private String nombreMedicamento;

    private double nuevoPrecio;

    // 📌 Constructor: cuando se dispara el evento, se pasa la información relevante
    //    que describe el cambio ocurrido.
    public MedicamentoActualizadoEvent(Long medicamentoId, String nombreMedicamento, double nuevoPrecio) {
        this.medicamentoId = medicamentoId;
        this.nombreMedicamento = nombreMedicamento;
        this.nuevoPrecio = nuevoPrecio;
    }

    // 📌 "Getter" para LISTENERS que reciban este evento
    //    puedan acceder a la información y tomar decisiones )
    public Long getMedicamentoId() { return medicamentoId; }
    public String getNombreMedicamento() { return nombreMedicamento; }
    public double getNuevoPrecio() { return nuevoPrecio; }
}


