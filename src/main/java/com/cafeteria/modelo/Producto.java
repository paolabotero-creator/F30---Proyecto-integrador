package com.cafeteria.modelo;

public abstract class Producto {

    // Atributos
    private String nombre;
    private double precio;

    // Constructor
    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    // Obtener nombre
    public String getNombre() {
        return nombre;
    }

    // Obtener precio
    public double getPrecio() {
        return precio;
    }

    // Modificar nombre
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Modificar precio
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    // Método abstracto
    public abstract String obtenerTipo();
}