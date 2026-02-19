/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bancobackend.dao;
import bancobackend.bd.dbConnection;
import bancoBackend.modelo.Cuenta;
import java.sql.*;
/**
 *
 * @author Julio Acuña
 */
public class CuentaDAO {
    
public int crearCuenta(String nombre) throws SQLException {
        String sql = "INSERT INTO cuentas (nombre, saldo) VALUES (?, 0)";

        try (Connection con = dbConnection.conectar();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, nombre);
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }

        return -1; // si no se pudo obtener el id
    }

    public boolean existeCuenta(int idCuenta) throws SQLException {
        String sql = "SELECT 1 FROM cuentas WHERE id = ?";

        try (Connection con = dbConnection.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCuenta);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public double obtenerSaldo(int idCuenta) throws SQLException {
        String sql = "SELECT saldo FROM cuentas WHERE id = ?";

        try (Connection con = dbConnection.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCuenta);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("saldo");
                }
            }
        }

        return -1; // si no existe o falla
    }

    public void actualizarSaldo(int idCuenta, double nuevoSaldo) throws SQLException {
        String sql = "UPDATE cuentas SET saldo = ? WHERE id = ?";

        try (Connection con = dbConnection.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, nuevoSaldo);
            ps.setInt(2, idCuenta);
            ps.executeUpdate();
        }
    }

    // (Opcional pro) Obtener una cuenta completa
    public Cuenta obtenerCuentaPorId(int idCuenta) throws SQLException {
        String sql = "SELECT id, nombre, saldo FROM cuentas WHERE id = ?";

        try (Connection con = dbConnection.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCuenta);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Cuenta(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getDouble("saldo")
                    );
                }
            }
        }

        return null;
    }
}