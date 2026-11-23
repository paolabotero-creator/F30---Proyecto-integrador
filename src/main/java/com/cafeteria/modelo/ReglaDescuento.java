package com.cafeteria.modelo;

@FunctionalInterface
public interface ReglaDescuento {

    double calcularDescuento(double total);

}