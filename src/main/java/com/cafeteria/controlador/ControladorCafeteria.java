
package com.cafeteria.controlador;

import com.cafeteria.modelo.CalculadoraDescuento;
import com.cafeteria.vista.VentanaCafeteria;
import com.cafeteria.conexion.ConexionBD;

import java.util.Locale;

public class ControladorCafeteria {

    private final VentanaCafeteria vista;
    private final CalculadoraDescuento calculadora;
    private boolean conexionVerificada = false;

    // Constructor
    public ControladorCafeteria(VentanaCafeteria vista) {

        this.vista = vista;

        // Crear la calculadora con la regla predeterminada
        this.calculadora = new CalculadoraDescuento(
                CalculadoraDescuento.crearReglaPredeterminada()
        );

        // Estado inicial: cálculo deshabilitado y resultados vacíos
        this.vista.habilitarCalculo(false);
        this.vista.actualizarEstadoConexion(false);
        this.vista.mostrarResultado("");

        // Evento del botón Conectar
        this.vista.getBtnConectar().addActionListener(e -> conectarBD());

        // Evento del botón Calcular descuento
        this.vista.getBtnCalcular().addActionListener(e -> {

            if (!conexionVerificada) {
                this.vista.mostrarResultado(
                        "Debe conectar primero con la base de datos."
                );
                return;
            }

            calcularPedido();
        });
    }

    // Probar conexión con la URL ingresada
    private void conectarBD() {

        // Obtener la URL ingresada
        String url = this.vista.getUrlIngresada();

        // Ejecutar prueba de conexión
        String resultado = ConexionBD.probarConexion(url);

        // Comprobar el resultado
        conexionVerificada = resultado.startsWith(
                "Conexión a SQLite establecida correctamente."
        );

        // Actualizar el indicador visual
        this.vista.actualizarEstadoConexion(conexionVerificada);

        // Habilitar o deshabilitar el cálculo
        this.vista.habilitarCalculo(conexionVerificada);

        // Mostrar mensaje
        this.vista.mostrarResultado(resultado);
    }

    // Calcular descuento del pedido
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
