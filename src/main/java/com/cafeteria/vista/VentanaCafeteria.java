
package com.cafeteria.vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VentanaCafeteria extends JFrame {

    // Colores de la interfaz
    private final Color COLOR_FONDO = new Color(243, 246, 250);
    private final Color COLOR_TARJETA = Color.WHITE;
    private final Color COLOR_AZUL = new Color(37, 99, 235);
    private final Color COLOR_TEXTO = new Color(30, 41, 59);
    private final Color COLOR_VERDE = new Color(22, 163, 74);
    private final Color COLOR_ROJO = new Color(220, 38, 38);

    // Componentes
    private JTextField txtUrl;
    private JTextField txtTotal;

    private JButton btnConectar;
    private JButton btnCalcular;

    private JTextArea txtResultado;

    private JLabel lblIndicador;
    private JLabel lblEstadoConexion;

    // Constructor

    public VentanaCafeteria() {

        setTitle("Sistema de Descuentos - Cafetería");
        setSize(760, 820);
        setMinimumSize(new Dimension(680, 760));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Fondo de la ventana
        JPanel fondo = new JPanel(new GridBagLayout());
        fondo.setBackground(COLOR_FONDO);

        // Contenedor central
        JPanel contenido = new JPanel(new GridBagLayout());
        contenido.setOpaque(false);
        contenido.setBorder(new EmptyBorder(20, 25, 20, 25));
        contenido.setPreferredSize(new Dimension(620, 740));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 16, 0);

        // ENCABEZADO
        JPanel encabezado = new JPanel();
        encabezado.setLayout(new BoxLayout(encabezado, BoxLayout.Y_AXIS));
        encabezado.setOpaque(false);

        JLabel lblTitulo = new JLabel(
                "GESTIÓN DE DESCUENTOS",
                SwingConstants.CENTER
        );

        lblTitulo.setFont(new Font("Arial", Font.BOLD, 26));
        lblTitulo.setForeground(COLOR_TEXTO);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSubtitulo = new JLabel(
                "SISTEMA DE ADMINISTRACIÓN DE CAFETERÍA"
        );

        lblSubtitulo.setFont(new Font("Arial", Font.PLAIN, 12));
        lblSubtitulo.setForeground(new Color(100, 116, 139));
        lblSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        encabezado.add(lblTitulo);
        encabezado.add(Box.createVerticalStrut(6));
        encabezado.add(lblSubtitulo);

        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 25, 0);
        contenido.add(encabezado, gbc);

        // TARJETA DE CONEXIÓN
        JPanel tarjetaConexion = crearTarjeta();

        JLabel lblUrl = new JLabel("URL de conexión JDBC");
        estilizarEtiqueta(lblUrl);

        txtUrl = new JTextField("jdbc:sqlite:inventario.db");
        estilizarCampo(txtUrl);

        btnConectar = new BotonRedondeado("Conectar", COLOR_AZUL);
        btnConectar.setPreferredSize(new Dimension(145, 42));

        // Indicador de conexión
        lblIndicador = new JLabel("●");
        lblIndicador.setFont(new Font("Arial", Font.BOLD, 16));
        lblIndicador.setForeground(COLOR_ROJO);

        lblEstadoConexion = new JLabel("Desconectado");
        lblEstadoConexion.setFont(new Font("Arial", Font.BOLD, 13));
        lblEstadoConexion.setForeground(COLOR_ROJO);

        JPanel estadoPanel = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 5, 10)
        );
        estadoPanel.setOpaque(false);
        estadoPanel.add(lblIndicador);
        estadoPanel.add(lblEstadoConexion);

        JPanel filaConexion = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 0, 0)
        );
        filaConexion.setOpaque(false);
        filaConexion.setAlignmentX(Component.LEFT_ALIGNMENT);

        filaConexion.add(btnConectar);
        filaConexion.add(Box.createHorizontalStrut(18));
        filaConexion.add(estadoPanel);

        tarjetaConexion.add(lblUrl);
        tarjetaConexion.add(Box.createVerticalStrut(10));
        tarjetaConexion.add(txtUrl);
        tarjetaConexion.add(Box.createVerticalStrut(18));
        tarjetaConexion.add(filaConexion);

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 16, 0);
        contenido.add(tarjetaConexion, gbc);

        // TARJETA DE DESCUENTOS
        JPanel tarjetaDescuento = crearTarjeta();

        JLabel lblTotal = new JLabel("Ingrese el total del pedido");
        estilizarEtiqueta(lblTotal);

        txtTotal = new JTextField();
        estilizarCampo(txtTotal);

        btnCalcular = new BotonRedondeado(
                "Calcular descuento",
                COLOR_AZUL
        );

        btnCalcular.setPreferredSize(new Dimension(100, 44));
        btnCalcular.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 44)
        );
        btnCalcular.setEnabled(false);

        tarjetaDescuento.add(lblTotal);
        tarjetaDescuento.add(Box.createVerticalStrut(10));
        tarjetaDescuento.add(txtTotal);
        tarjetaDescuento.add(Box.createVerticalStrut(18));
        tarjetaDescuento.add(btnCalcular);

        gbc.gridy = 2;
        contenido.add(tarjetaDescuento, gbc);

        // TARJETA DE RESULTADOS
        JPanel tarjetaResultados = crearTarjeta();

        JLabel lblResultados = new JLabel("Resultados");
        estilizarEtiqueta(lblResultados);

        txtResultado = new JTextArea(6, 30);
        txtResultado.setEditable(false);
        txtResultado.setFont(new Font("Arial", Font.PLAIN, 14));
        txtResultado.setForeground(COLOR_TEXTO);
        txtResultado.setBackground(new Color(248, 250, 252));
        txtResultado.setLineWrap(true);
        txtResultado.setWrapStyleWord(true);
        txtResultado.setMargin(new Insets(12, 12, 12, 12));

        JScrollPane scroll = new JScrollPane(
                txtResultado,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        scroll.setPreferredSize(new Dimension(100, 150));
        scroll.setAlignmentX(Component.LEFT_ALIGNMENT);

        tarjetaResultados.add(lblResultados);
        tarjetaResultados.add(Box.createVerticalStrut(10));
        tarjetaResultados.add(scroll);

        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 0, 0);
        contenido.add(tarjetaResultados, gbc);

        // Centrar el contenido dentro de la ventana
        GridBagConstraints gbcFondo = new GridBagConstraints();
        gbcFondo.gridx = 0;
        gbcFondo.gridy = 0;
        gbcFondo.anchor = GridBagConstraints.CENTER;

        fondo.add(contenido, gbcFondo);

        setContentPane(fondo);
    }

    // Crear tarjetas
    private JPanel crearTarjeta() {

        JPanel tarjeta = new JPanel();

        tarjeta.setLayout(
                new BoxLayout(tarjeta, BoxLayout.Y_AXIS)
        );

        tarjeta.setBackground(COLOR_TARJETA);

        tarjeta.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(226, 232, 240)
                        ),
                        new EmptyBorder(20, 22, 20, 22)
                )
        );

        tarjeta.setAlignmentX(Component.CENTER_ALIGNMENT);

        return tarjeta;
    }

    // Estilizar etiquetas
    private void estilizarEtiqueta(JLabel etiqueta) {

        etiqueta.setFont(new Font("Arial", Font.BOLD, 14));
        etiqueta.setForeground(COLOR_TEXTO);
        etiqueta.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    // Estilizar campos de texto
    private void estilizarCampo(JTextField campo) {

        campo.setFont(new Font("Arial", Font.PLAIN, 14));
        campo.setForeground(COLOR_TEXTO);
        campo.setBackground(Color.WHITE);

        campo.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(203, 213, 225)
                        ),
                        new EmptyBorder(9, 10, 9, 10)
                )
        );

        campo.setPreferredSize(new Dimension(100, 42));
        campo.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 42)
        );

        campo.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    // Actualizar indicador de conexión
    public void actualizarEstadoConexion(boolean conectado) {

        if (conectado) {

            lblIndicador.setForeground(COLOR_VERDE);
            lblEstadoConexion.setForeground(COLOR_VERDE);

            lblEstadoConexion.setText("Conectado");

        } else {

            lblIndicador.setForeground(COLOR_ROJO);
            lblEstadoConexion.setForeground(COLOR_ROJO);

            lblEstadoConexion.setText("Desconectado");
        }
    }

    // Obtener URL
    public String getUrlIngresada() {
        return txtUrl.getText().trim();
    }

    // Obtener campo URL para detectar cambios
    public JTextField getTxtUrl() {
        return txtUrl;
    }

    // Obtener monto
    public String getTotalIngresado() {
        return txtTotal.getText();
    }

    // Obtener botón conectar
    public JButton getBtnConectar() {
        return btnConectar;
    }

    // Obtener botón calcular
    public JButton getBtnCalcular() {
        return btnCalcular;
    }

    // Habilitar o deshabilitar cálculo
    public void habilitarCalculo(boolean habilitado) {
        btnCalcular.setEnabled(habilitado);
    }

    // Mostrar resultados
    public void mostrarResultado(String resultado) {
        txtResultado.setText(resultado);
    }

    // Botón personalizado con esquinas redondeadas
    private static class BotonRedondeado extends JButton {

        private final Color colorBase;

        public BotonRedondeado(String texto, Color color) {

            super(texto);

            this.colorBase = color;

            setFont(new Font("Arial", Font.BOLD, 14));
            setForeground(Color.WHITE);
            setBackground(color);

            setFocusPainted(false);
            setBorderPainted(false);
            setContentAreaFilled(false);
            setOpaque(false);

            setCursor(new Cursor(Cursor.HAND_CURSOR));

            setBorder(
                    new EmptyBorder(10, 18, 10, 18)
            );
        }

        @Override
        protected void paintComponent(Graphics g) {

            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            Color color = colorBase;

            if (!isEnabled()) {

                color = new Color(180, 190, 205);

            } else if (getModel().isPressed()) {

                color = colorBase.darker();

            } else if (getModel().isRollover()) {

                color = colorBase.brighter();
            }

            g2.setColor(color);

            g2.fillRoundRect(
                    0, 0,
                    getWidth(), getHeight(),
                    16, 16
            );

            g2.dispose();

            super.paintComponent(g);
        }
    }
}


/*package com.cafeteria.vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VentanaCafeteria extends JFrame {

    // Colores interfaz
    private final Color COLOR_FONDO = new Color(243, 246, 250);
    private final Color COLOR_TARJETA = Color.WHITE;
    private final Color COLOR_AZUL = new Color(37, 99, 235);
    private final Color COLOR_TEXTO = new Color(30, 41, 59);
    private final Color COLOR_VERDE = new Color(22, 163, 74);
    private final Color COLOR_ROJO = new Color(220, 38, 38);

    // Componentes de la interfaz
    private JTextField txtUrl;
    private JTextField txtTotal;
    private JButton btnCalcular;
    private JTextArea txtResultado;
    private JButton btnConectar;
    private JLabel lblIndicator;
    private JLabel lblConectionStatus;
    // Constructor
    public VentanaCafeteria() {

        setTitle("Sistema de Descuentos - Cafetería");
        setSize(620, 690);
        setMinimumSize(new Dimension(540, 620));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBackground(COLOR_FONDO);
        panelPrincipal.setBorder(new EmptyBorder(25, 30, 25, 30));

        // Título
        JLabel lblTitulo = new JLabel(
                "GESTIÓN DE DESCUENTOS",
                SwingConstants.CENTER
        );

        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));

        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        // Panel de conexión
        JPanel panelCampos = new JPanel(new BorderLayout(10, 10));

        // Fila de la URL
        JPanel filaUrl = new JPanel(new BorderLayout(10, 10));

        JLabel lblUrl = new JLabel("URL de conexión:");

        txtUrl = new JTextField("jdbc:sqlite:inventario.db");

        btnConectar = new JButton("Conectar");

        filaUrl.add(lblUrl, BorderLayout.WEST);
        filaUrl.add(txtUrl, BorderLayout.CENTER);

        // Botón debajo de la URL
        panelCampos.add(filaUrl, BorderLayout.NORTH);
        panelCampos.add(btnConectar, BorderLayout.SOUTH);

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

        // Panel central: URL y entrada del monto
        JPanel panelCentral = new JPanel(new BorderLayout(10, 10));

        panelCentral.add(panelCampos, BorderLayout.NORTH);
        panelCentral.add(panelEntrada, BorderLayout.CENTER);

        // Panel de resultados
        txtResultado = new JTextArea(7, 25);
        txtResultado.setEditable(false);
        txtResultado.setFont(new Font("Arial", Font.PLAIN, 14));
        txtResultado.setLineWrap(true);
        txtResultado.setWrapStyleWord(true);
        txtResultado.setText("");

        JScrollPane scroll = new JScrollPane(txtResultado);

        // Agregar componentes
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);
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

    // Obtener URL ingresada
    public String getUrlIngresada() {
        return txtUrl.getText().trim();
    }

    // Obtener botón conectar
    public JButton getBtnConectar() {
        return btnConectar;
    }

    // Obtener campo URL
    public JTextField getTxtUrl() {
        return txtUrl;
    }

    // Habilitar o deshabilitar el cálculo
    public void habilitarCalculo(boolean habilitado) {
        btnCalcular.setEnabled(habilitado);
    }
    }*/