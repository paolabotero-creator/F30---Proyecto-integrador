package com.cafeteria;

import com.cafeteria.vista.VentanaCafeteria;
import com.cafeteria.controlador.ControladorCafeteria;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            // Crear la ventana
            VentanaCafeteria vista = new VentanaCafeteria();

            // Crear el controlador
            new ControladorCafeteria(vista);

            // Mostrar la aplicación
            vista.setVisible(true);

        });
    }
}