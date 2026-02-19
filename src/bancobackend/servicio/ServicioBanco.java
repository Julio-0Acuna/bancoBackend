/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bancobackend.servicio;
import bancobackend.dao.CuentaDAO;
import bancobackend.dao.TransaccionDAO;
import bancobackend.modelo.Transaccion;

import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author Julio Acuña
 */
public class ServicioBanco {
private final CuentaDAO cuentaDAO;
    private final TransaccionDAO transaccionDAO;

    public ServicioBanco() {
        this.cuentaDAO = new CuentaDAO();
        this.transaccionDAO = new TransaccionDAO();
    }

    // ✅ Crear cuenta (devuelve id)
    public int crearCuenta(String nombre) throws SQLException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        return cuentaDAO.crearCuenta(nombre.trim());
    }

    // ✅ Depositar
    public void depositar(int idCuenta, double monto) throws SQLException {
        validarCuenta(idCuenta);
        validarMonto(monto);

        double saldoActual = cuentaDAO.obtenerSaldo(idCuenta);
        double nuevoSaldo = saldoActual + monto;

        // 1) actualizar saldo
        cuentaDAO.actualizarSaldo(idCuenta, nuevoSaldo);

        // 2) registrar transacción
        transaccionDAO.registrarTransaccion(idCuenta, "DEPOSITO", monto);
    }

    // ✅ Retirar
    public void retirar(int idCuenta, double monto) throws SQLException {
        validarCuenta(idCuenta);
        validarMonto(monto);

        double saldoActual = cuentaDAO.obtenerSaldo(idCuenta);

        if (monto > saldoActual) {
            throw new IllegalArgumentException("Saldo insuficiente. Saldo actual: " + saldoActual);
        }

        double nuevoSaldo = saldoActual - monto;

        // 1) actualizar saldo
        cuentaDAO.actualizarSaldo(idCuenta, nuevoSaldo);

        // 2) registrar transacción
        transaccionDAO.registrarTransaccion(idCuenta, "RETIRO", monto);
    }

    // ✅ Ver saldo
    public double verSaldo(int idCuenta) throws SQLException {
        validarCuenta(idCuenta);
        return cuentaDAO.obtenerSaldo(idCuenta);
    }

    // ✅ Historial completo
    public ArrayList<Transaccion> verHistorial(int idCuenta) throws SQLException {
        validarCuenta(idCuenta);
        return transaccionDAO.listarHistorial(idCuenta);
    }

    // ✅ Últimas N
    public ArrayList<Transaccion> verUltimasN(int idCuenta, int n) throws SQLException {
        validarCuenta(idCuenta);

        if (n <= 0) {
            throw new IllegalArgumentException("N debe ser mayor que 0.");
        }

        return transaccionDAO.listarUltimasN(idCuenta, n);
    }

    // ✅ Reporte (transacciones totales)
    public int totalTransacciones() throws SQLException {
        return transaccionDAO.contarTransacciones();
    }

    // ===================== VALIDACIONES =====================

    private void validarMonto(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor que 0.");
        }
    }

    private void validarCuenta(int idCuenta) throws SQLException {
        if (idCuenta <= 0) {
            throw new IllegalArgumentException("ID de cuenta inválido.");
        }

        boolean existe = cuentaDAO.existeCuenta(idCuenta);
        if (!existe) {
            throw new IllegalArgumentException("La cuenta no existe.");
        }
    }
}