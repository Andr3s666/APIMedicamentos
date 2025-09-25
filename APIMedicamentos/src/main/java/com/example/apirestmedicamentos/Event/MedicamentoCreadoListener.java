package com.example.apirestmedicamentos.Event;

import org.springframework.scheduling.annotation.Async;  // 🆕 Para async
import org.springframework.stereotype.Component;       // 🆕 Para que Spring lo detecte
import org.springframework.context.event.EventListener; // 🆕 Para escuchar eventos

@Component // 🆕 Esta clase es importante, crea y adminístra"
public class MedicamentoCreadoListener {

    // 🆕 Este método se ejecuta AUTOMÁTICAMENTE cuando alguien publica MedicamentoCreadoEvent
    @EventListener // 🆕 Método escucha eventos de tipo MedicamentoCreadoEvent"
    @Async         // 🆕 Ejecuta esto en segundo plano, no bloquear la respuesta principal"
    public void manejarMedicamentoCreado(MedicamentoCreadoEvent evento) {

        // 🆕 ESTO ES LO QUE PASA CUANDO SE CREA UN MEDICAMENTO:

        // 1. Log en consola (simulando que hacemos algo útil)
        System.out.println("🎉 EVENTO RECIBIDO: Se creó un nuevo medicamento!");
        System.out.println("📋 ID: " + evento.getMedicamentoId());
        System.out.println("💊 Nombre: " + evento.getNombreMedicamento());
        System.out.println("⏰ Fecha: " + new java.util.Date());


        System.out.println("✅ EVENTO PROCESADO (todo en segundo plano)");
    }


    @EventListener
    @Async
    public void manejarMedicamentoEliminado(MedicamentoEliminadoEvent evento) {
        System.out.println(" EVENTO ELIMINACIÓN: Se borró un medicamento!");
        System.out.println(" ID: " + evento.getMedicamentoId());
        System.out.println(" Nombre: " + evento.getNombreMedicamento());
        System.out.println(" Fecha: " + new java.util.Date());
    }

    @EventListener
    @Async
    public void manejarMedicamentoActualizado(MedicamentoActualizadoEvent evento) {
        System.out.println("✏️ EVENTO ACTUALIZACIÓN: Se modificó un medicamento!");
        System.out.println("📋 ID: " + evento.getMedicamentoId());
        System.out.println("💊 Nombre: " + evento.getNombreMedicamento());
        System.out.println("💰 Nuevo precio: $" + evento.getNuevoPrecio());
        System.out.println("⏰ Fecha: " + new java.util.Date());
    }



}




