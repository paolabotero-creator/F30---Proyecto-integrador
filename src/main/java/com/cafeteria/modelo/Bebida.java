package com.cafeteria.modelo;

public class Bebida extends Producto {

    private String tamano;

    public Bebida(String nombre, double precio, String tamano) {
        super(nombre, precio);
        this.tamano = tamano;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    @Override
    public String obtenerTipo() {
        return "Bebida";
    }
}
