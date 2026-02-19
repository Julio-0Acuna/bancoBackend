/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bancobackend.modelo;
import java.sql.Timestamp;
/**
 *
 * @author julio
 */
public class Transaccion {
    private int id;
    private int idCuenta;
    private String tipo; // "DEPOSITO" o "RETIRO"
    private double monto;
    private Timestamp fecha;
  public Transaccion() {
    }

    public Transaccion(int id, int idCuenta, String tipo, double monto, Timestamp fecha) {
        this.id = id;
        this.idCuenta = idCuenta;
        this.tipo = tipo;
        this.monto = monto;
        this.fecha = fecha;
    }

    public Transaccion(int idCuenta, String tipo, double monto) {
        this.idCuenta = idCuenta;
        this.tipo = tipo;
        this.monto = monto;
    }

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Transaccion{" +
                "id=" + id +
                ", idCuenta=" + idCuenta +
                ", tipo='" + tipo + '\'' +
                ", monto=" + monto +
                ", fecha=" + fecha +
                '}';
    }
}