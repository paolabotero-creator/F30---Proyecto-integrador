package com.cafeteria.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    // URL de conexión a SQLite
    private static final String URL = "jdbc:sqlite:cafeteria.db";

    // Probar conexión con la base de datos
    public static String probarConexion() {

        try {
            // Cargar el controlador JDBC
            Class.forName("org.sqlite.JDBC");

            // Establecer y cerrar la conexión automáticamente
            try (Connection conexion = DriverManager.getConnection(URL)) {

                return "Conexión a SQLite establecida correctamente.";

            }

        } catch (ClassNotFoundException e) {

            return "Error: No se encontró el controlador JDBC. "
                    + e.getMessage();

        } catch (SQLException e) {

            return "Error al conectar con SQLite: "
                    + e.getMessage();
        }
    }

    // Obtener una conexión abierta con SQLite
    public static Connection obtenerConexion()
            throws ClassNotFoundException, SQLException {

        // Cargar el controlador JDBC
        Class.forName("org.sqlite.JDBC");

        // Devolver la conexión
        return DriverManager.getConnection(URL);
    }

}