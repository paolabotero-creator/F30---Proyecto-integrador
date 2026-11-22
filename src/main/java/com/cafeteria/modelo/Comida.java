package com.cafeteria.modelo;

public class Comida extends Producto {

    private String categoria;

    public Comida(String nombre, double precio, String categoria) {
        super(nombre, precio);
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String obtenerTipo() {
        return "Comida";
    }
}
