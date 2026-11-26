package com.cafeteria.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    // Probar conexión utilizando la URL ingresada
    public static String probarConexion(String url) {

        if (url == null || url.trim().isEmpty()) {
            return "Error: La URL de conexión es obligatoria.";
        }

        try (Connection conexion = obtenerConexion(url)) {

            return "Conexión a SQLite establecida correctamente.";

        } catch (ClassNotFoundException e) {

            return "Error: No se encontró el controlador JDBC. "
                    + e.getMessage();

        } catch (SQLException e) {

            return "Error al conectar con SQLite: "
                    + e.getMessage();
        }
    }

    // Obtener una conexión abierta utilizando la URL recibida
    public static Connection obtenerConexion(String url)
            throws ClassNotFoundException, SQLException {

        if (url == null || url.trim().isEmpty()) {
            throw new SQLException("La URL de conexión está vacía.");
        }

        // Cargar el controlador JDBC
        Class.forName("org.sqlite.JDBC");

        // Establecer conexión
        return DriverManager.getConnection(url.trim());
    }
}
