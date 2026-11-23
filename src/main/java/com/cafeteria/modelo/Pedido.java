package com.cafeteria.modelo;

import java.time.LocalDateTime;

public class Pedido {

    // Atributos
    private int id;
    private double total;
    private double descuento;
    private double totalFinal;
    private LocalDateTime fecha;

    // Constructor
    public Pedido(double total, double descuento,
                  double totalFinal) {

        this.total = total;
        this.descuento = descuento;
        this.totalFinal = totalFinal;
        this.fecha = LocalDateTime.now();
    }

    // Obtener identificador
    public int getId() {
        return id;
    }

    // Asignar identificador
    public void setId(int id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public double getDescuento() {
        return descuento;
    }

    public double getTotalFinal() {
        return totalFinal;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }
}