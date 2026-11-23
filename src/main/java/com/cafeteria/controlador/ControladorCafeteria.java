package com.cafeteria.controlador;

import com.cafeteria.modelo.CalculadoraDescuento;
import com.cafeteria.vista.VentanaCafeteria;
import com.cafeteria.conexion.ConexionBD;

import java.util.Locale;

public class ControladorCafeteria {

    private final VentanaCafeteria vista;
    private final CalculadoraDescuento calculadora;

    public ControladorCafeteria(VentanaCafeteria vista) {

        this.vista = vista;

        this.calculadora = new CalculadoraDescuento(
                CalculadoraDescuento.crearReglaPredeterminada()
        );

        this.vista.getBtnCalcular().addActionListener( //Detecta cuando el usuario presiona el botón
                e -> calcularPedido() //Expresión lambda para ejecutar el cálculo
        );

        this.vista.mostrarResultado(
                ConexionBD.probarConexion() //Conexión JDBC
        );
    }

    private void calcularPedido() {

        try {

            double total = Double.parseDouble(
                    vista.getTotalIngresado().trim()
            );

            if (total <= 0) {
                vista.mostrarResultado(
                        "Ingrese un valor mayor que cero."
                );
                return;
            }

            double descuento = calculadora.calcularDescuento(total);

            double totalFinal = calculadora.calcularTotalFinal(total);

            String resultado = String.format(
                    Locale.US,
                    "Total del pedido: $%.2f%n" +
                            "Descuento aplicado: $%.2f%n" +
                            "Total a pagar: $%.2f",
                    total,
                    descuento,
                    totalFinal
            );

            vista.mostrarResultado(resultado);

        } catch (NumberFormatException e) {

            vista.mostrarResultado(
                    "Error: ingrese un valor numérico válido."
            );
        }
    }
}
