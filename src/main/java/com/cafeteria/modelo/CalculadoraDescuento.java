package com.cafeteria.modelo;

public class CalculadoraDescuento {

    // Regla de descuento
    private final ReglaDescuento regla;

    // Constructor
    public CalculadoraDescuento(ReglaDescuento regla) {
        this.regla = regla;
    }

    // Crear regla de descuento mediante lambda
    public static ReglaDescuento crearReglaPredeterminada() {

        return total -> total > 10 ? total * 0.15 : 0;

    }

    // Calcular el descuento
    public double calcularDescuento(double total) {
        return regla.calcularDescuento(total);
    }

    // Calcular el total final
    public double calcularTotalFinal(double total) {

        double descuento = calcularDescuento(total);

        return total - descuento;
    }
}