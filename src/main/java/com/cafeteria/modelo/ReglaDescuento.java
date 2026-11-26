package com.cafeteria.modelo;
//Aplicación SOLID
@FunctionalInterface
public interface ReglaDescuento {

    double calcularDescuento(double total);

}