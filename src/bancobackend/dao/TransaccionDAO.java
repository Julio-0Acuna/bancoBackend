/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bancobackend.dao;
import bancobackend.bd.dbConnection;
import bancobackend.modelo.Transaccion;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Julio Acuña
 */
public class TransaccionDAO {
   // INSERT: registrar una transacción
    public void registrarTransaccion(int idCuenta, String tipo, double monto) throws SQLException {
        String sql = "INSERT INTO transacciones (cuenta_id, tipo, monto) VALUES (?, ?, ?)";

        try (Connection con = dbConnection.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCuenta);
            ps.setString(2, tipo);
            ps.setDouble(3, monto);

            ps.executeUpdate();
        }
    }

    // SELECT: historial completo (DESC)
    public ArrayList<Transaccion> listarHistorial(int idCuenta) throws SQLException {
        String sql = """
                SELECT id, cuenta_id, tipo, monto, fecha
                FROM transacciones
                WHERE cuenta_id = ?
                ORDER BY fecha DESC
                """;

        ArrayList<Transaccion> lista = new ArrayList<>();

        try (Connection con = dbConnection.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCuenta);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Transaccion t = new Transaccion(
                            rs.getInt("id"),
                            rs.getInt("cuenta_id"),
                            rs.getString("tipo"),
                            rs.getDouble("monto"),
                            rs.getTimestamp("fecha")
                    );
                    lista.add(t);
                }
            }
        }

        return lista;
    }

    // SELECT: últimas N transacciones (DESC)
    public ArrayList<Transaccion> listarUltimasN(int idCuenta, int n) throws SQLException {
        String sql = """
                SELECT id, cuenta_id, tipo, monto, fecha
                FROM transacciones
                WHERE cuenta_id = ?
                ORDER BY fecha DESC
                LIMIT ?
                """;

        ArrayList<Transaccion> lista = new ArrayList<>();

        try (Connection con = dbConnection.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCuenta);
            ps.setInt(2, n);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Transaccion t = new Transaccion(
                            rs.getInt("id"),
                            rs.getInt("cuenta_id"),
                            rs.getString("tipo"),
                            rs.getDouble("monto"),
                            rs.getTimestamp("fecha")
                    );
                    lista.add(t);
                }
            }
        }

        return lista;
    }

    // REPORTE: total transacciones (para reporte general)
    public int contarTransacciones() throws SQLException {
        String sql = "SELECT COUNT(*) AS total FROM transacciones";

        try (Connection con = dbConnection.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("total");
            }
        }

        return 0;
    }
} 

