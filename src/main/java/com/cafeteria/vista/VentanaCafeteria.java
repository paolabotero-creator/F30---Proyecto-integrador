package com.cafeteria.vista;

import javax.swing.*;
import java.awt.*;

public class VentanaCafeteria extends JFrame {

    // Componentes de la interfaz
    private JTextField txtTotal;
    private JButton btnCalcular;
    private JTextArea txtResultado;

    // Constructor
    public VentanaCafeteria() {

        setTitle("Sistema de Descuentos - Cafetería");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout(10, 10));
        panelPrincipal.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        // Título
        JLabel lblTitulo = new JLabel(
                "GESTIÓN DE DESCUENTOS",
                SwingConstants.CENTER
        );

        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));

        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        // Panel de entrada
        JPanel panelEntrada = new JPanel(
                new GridLayout(3, 1, 10, 10)
        );

        JLabel lblTotal = new JLabel("Ingrese el total del pedido:");

        txtTotal = new JTextField();
        txtTotal.setFont(new Font("Arial", Font.PLAIN, 16));

        btnCalcular = new JButton("Calcular descuento");

        panelEntrada.add(lblTotal);
        panelEntrada.add(txtTotal);
        panelEntrada.add(btnCalcular);

        // Panel de resultados
        txtResultado = new JTextArea(7, 25);
        txtResultado.setEditable(false);
        txtResultado.setFont(new Font("Arial", Font.PLAIN, 14));
        txtResultado.setLineWrap(true);
        txtResultado.setWrapStyleWord(true);

        JScrollPane scroll = new JScrollPane(txtResultado);

        // Agregar componentes
        panelPrincipal.add(panelEntrada, BorderLayout.CENTER);
        panelPrincipal.add(scroll, BorderLayout.SOUTH);

        add(panelPrincipal);
    }

    // Obtener el monto ingresado
    public String getTotalIngresado() {
        return txtTotal.getText();
    }

    // Obtener botón
    public JButton getBtnCalcular() {
        return btnCalcular;
    }

    // Mostrar resultados
    public void mostrarResultado(String resultado) {
        txtResultado.setText(resultado);
    }
}